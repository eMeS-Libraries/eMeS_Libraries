package lv.emes.libraries.communication.json;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JSON object extended from {@link JSONObject} that provides additional utilities and constructors.
 *
 * @author eMeS
 * @version 2.3.
 * @since 2.2.2
 */
public class MS_JSONObject extends JSONObject implements MS_JSON {

    private static boolean setupDone = false;
    private static Field JSONObjectMapField = null;

    //*** New constructors ***

    /**
     * Creates new instance of JSON object, which have its fields ordered by insertion order.
     */
    public MS_JSONObject() {
        super();
        setupJSONFieldAccessor();
        try {
            if (JSONObjectMapField != null)
                JSONObjectMapField.set(this, new LinkedHashMap<>());
        } catch (IllegalAccessException ignored) {
        }
    }

    /**
     * Creates new JSON object from given org.json object (or its subclass, including this) by using its original map.
     *
     * @param obj JSON.org object.
     */
    public MS_JSONObject(JSONObject obj) {
        this(obj == null ? null : obj.toString());
    }

    //*** Overridden constructors ***

    /**
     * Converts map into JSON object. If map is null then empty object is returned.
     *
     * @param map a map object that can be used to initialize the contents of the MS_JSONObject.
     */
    public MS_JSONObject(Map<?, ?> map) {
        this();
        if (map != null) map.forEach((key, value) -> {
            if (key != null) {
                String k = key.toString();
                put(k, this.wrapInternal(value));
            }
        });
    }

    public MS_JSONObject(JSONObject jo, String... names) {
        this();
        for (String name : names) {
            try {
                this.putOnce(name, jo.opt(name));
            } catch (Exception ignore) {
            }
        }
    }

    public MS_JSONObject(JSONTokener x) throws JSONException {
        super(x);
    }

    /**
     * <u>Caution</u>: use only with real object beans that has valid field getters, as this method is using those
     * to populate JSON fields. In other cases, please, prefer: {@link MS_JSONObject#MS_JSONObject(Map)}!
     *
     * @param bean bean object with getters.
     */
    public MS_JSONObject(Object bean) {
        super(bean);
    }

    public MS_JSONObject(Object object, String[] names) {
        super(object, names);
    }

    public MS_JSONObject(String source) throws JSONException {
        super(source);
    }

    public MS_JSONObject(String baseName, Locale locale) throws JSONException {
        super(baseName, locale);
    }

    //*** Static utilities ***

    /**
     * Casts given object to JSON Object. It also supports {@link JSONObject}, but converts it
     * to {@link MS_JSONObject} in the process.
     *
     * @param fromObject any JSON Object object.
     * @return same instance of given <b>fromObject</b>, but casted to {@link MS_JSONObject} type.
     * @throws ClassCastException if <tt>fromObject = null</tt> or is not JSON Object.
     */
    public static MS_JSONObject cast(Object fromObject) {
        if (MS_JSONUtils.isOrgJsonObject(fromObject)) {
            return new MS_JSONObject(fromObject.toString());
        } else {
            return (MS_JSONObject) fromObject; //let ClassCastException fly in case of problems
        }
    }

    //*** Utilities ***

    public boolean isNull(String key) {
        return !this.has(key) || MS_JSONUtils.isJsonNull(this.get(key));
    }

    public boolean isNotNull(String key) {
        return !isNull(key);
    }

    public boolean isJsonObject(String key) {
        return this.has(key) && this.get(key) instanceof JSONObject;
    }

    public boolean isJsonArray(String key) {
        return this.has(key) && this.get(key) instanceof org.json.JSONArray;
    }

    public String[] getKeyArray() {
        String[] res = new String[this.keySet().size()];
        AtomicInteger i = new AtomicInteger(0);
        this.keySet().forEach(key -> res[i.getAndIncrement()] = key);
        return res;
    }

    //*** Methods from super class to override ***

    /**
     * As original {@link JSONObject#equals(Object)} is useless, this JSON object also has overridden
     * <code>equals</code> method, which uses {@link JSONObject#toString()} to compare JSON object with targeted object.
     *
     * @param obj object to compare with this instance.
     * @return true if given object is {@link JSONObject} instance and both {@link Object#toString()} are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;

        return obj instanceof JSONObject && (
                obj.toString().equals(this.toString()) || (
                        obj instanceof MS_JSONObject && this.isEqualTo((MS_JSONObject) obj)
                )
        );
    }

    private boolean isEqualTo(MS_JSONObject other) {
        if (this.length() != other.length()) return false;

        Iterator<String> keyIterator = this.keys();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            Object thisObject = this.get(key);
            Object otherObject = other.opt(key);
            if (!Objects.equals(thisObject, otherObject))
                return false;
        }
        return true;
    }

    @Override
    public MS_JSONArray getJSONArray(String key) throws JSONException {
        Object object = this.get(key);
        if (object instanceof MS_JSONArray) {
            return (MS_JSONArray) object;
        }
        throw new JSONException("MS_JSONObject[" + quote(key) + "] is not a MS_JSONArray.");
    }

    @Override
    public MS_JSONObject getJSONObject(String key) throws JSONException {
        Object object = this.get(key);
        if (object instanceof MS_JSONObject) {
            return (MS_JSONObject) object;
        }
        throw new JSONException("MS_JSONObject[" + quote(key) + "] is not a MS_JSONObject.");
    }

    public MS_JSON getJSON(String key) throws JSONException {
        Object object = this.get(key);
        if (object instanceof MS_JSONObject) {
            return (MS_JSONObject) object;
        } else if (object instanceof MS_JSONArray) {
            return (MS_JSONArray) object;
        }
        throw new JSONException("MS_JSONObject[" + quote(key) + "] is not a MS_JSON.");
    }

    @Override
    public MS_JSONArray optJSONArray(String key) {
        Object o = this.opt(key);
        return o instanceof org.json.JSONArray ? (MS_JSONArray) o : null;
    }

    public MS_JSONArray optJSONArray(String key, MS_JSONArray defaultValue) {
        MS_JSONArray res = optJSONArray(key);
        return res == null ? defaultValue : res;
    }

    @Override
    public MS_JSONObject optJSONObject(String key) {
        Object object = this.opt(key);
        return object instanceof JSONObject ? (MS_JSONObject) object : null;
    }

    public MS_JSONObject optJSONObject(String key, MS_JSONObject defaultValue) {
        MS_JSONObject res = optJSONObject(key);
        return res == null ? defaultValue : res;
    }

    @Override
    public MS_JSONObject put(String key, Object value) throws JSONException {
        super.put(key, this.wrapInternal(value));
        return this;
    }

    @Override
    public MS_JSONObject putOnce(String key, Object value) throws JSONException {
        super.putOnce(key, this.wrapInternal(value));
        return this;
    }

    @Override
    public MS_JSONObject accumulate(String key, Object value) throws JSONException {
        testValidity(value);
        value = this.wrapInternal(value);
        Object object = this.opt(key);
        if (object == null) {
            this.put(key, value instanceof MS_JSONArray ? new MS_JSONArray().put(value) : value);
        } else if (object instanceof MS_JSONArray) {
            ((MS_JSONArray) object).put(value);
        } else {
            this.put(key, new MS_JSONArray().put(object).put(value));
        }
        return this;
    }

    @Override
    public MS_JSONObject append(String key, Object value) throws JSONException {
        testValidity(value);
        value = this.wrapInternal(value);
        Object object = this.opt(key);
        if (object == null) {
            this.put(key, new MS_JSONArray().put(value));
        } else if (object instanceof MS_JSONArray) {
            this.put(key, ((MS_JSONArray) object).put(value));
        } else {
            throw new JSONException("MS_JSONObject[" + quote(key) + "] is not a MS_JSONArray.");
        }
        return this;
    }

    @Override
    public MS_JSONObject put(String key, boolean value) throws JSONException {
        super.put(key, value);
        return this;
    }

    @Override
    public MS_JSONObject put(String key, Collection<?> value) throws JSONException {
        super.put(key, value);
        return this;
    }

    @Override
    public MS_JSONObject put(String key, double value) throws JSONException {
        super.put(key, value);
        return this;
    }

    @Override
    public MS_JSONObject put(String key, int value) throws JSONException {
        super.put(key, value);
        return this;
    }

    @Override
    public MS_JSONObject put(String key, long value) throws JSONException {
        super.put(key, value);
        return this;
    }

    @Override
    public MS_JSONObject put(String key, Map<?, ?> value) throws JSONException {
        super.put(key, this.wrapInternal(value));
        return this;
    }

    @Override
    public MS_JSONObject putOpt(String key, Object value) throws JSONException {
        super.putOpt(key, this.wrapInternal(value));
        return this;
    }

    //*** Private methods ***

    private static void setupJSONFieldAccessor() {
        if (!setupDone) {
            setupDone = true;
            try {
                JSONObjectMapField = JSONObject.class.getDeclaredField("map");
                JSONObjectMapField.setAccessible(true);
            } catch (NoSuchFieldException ignored) {
            }
        }
    }
}
