package lv.emes.libraries.tools;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static java.awt.event.KeyEvent.*;
import static org.junit.Assert.*;

/**
 * Tests available key code dictionary. Recognizable inputs are:<br>
 *     <i>All single (input length = 1) digits and letters will be converted to ASCII code</i><br>
 CTRL = 17;<br>
 CONTROL = 17;<br>
 ALT = 18;<br>
 SHIFT = 16;<br>

 DEL = 46;<br>
 DELETE = 46;<br>

 INS = 45;<br>
 INSERT = 45;<br>

 HOME = 36;<br>
 END = 35;<br>
 PGUP = 33;<br>
 PG_UP = 33;<br>
 PGDOWN = 34;<br>
 PG_DOWN = 34;<br>

 SPACE = 32;<br>

 ESC = 27;<br>
 ESCAPE = 27;<br>

 ENT = 13;<br>
 ENTER = 13;<br>

 BACK = 8;<br>
 BCK = 8;<br>
 BACKSPACE = 8;<br>
 BCKS = 8;<br>

 TAB = 9;<br>
 CAPS = 20;<br>
 CAPSLOCK = 20;<br>
 CAPS_LOCK = 20;<br>

 F1 = 112;<br>
 F2 = 113;<br>
 F3 = 114;<br>
 F4 = 115;<br>
 F5 = 116;<br>
 F6 = 117;<br>
 F7 = 118;<br>
 F8 = 119;<br>
 F9 = 120;<br>
 F10 = 121;<br>
 F11 = 122;<br>
 F12 = 123;<br>

 MENU = 93;<br>
 RIGHT_MCLICK = 93;<br>

 WIN = 91;<br>
 WINDOWS = 91;<br>

 BACKSLASH = 220;<br>
 SLASH = 191;<br>

 LEFT = 37;<br>
 UP = 38;<br>
 RIGHT = 39;<br>
 DOWN = 40;<br>

 NUM_LOCK = 144;<br>
 NUMLOCK = 144;<br>

 | = 220;<br>
 \\ = 220;<br>
 ` = 192;<br>
 ~ = 192;<br>
 ! = 49;<br>
 @ = 50;<br>
 # = 51;<br>
 $ = 52;<br>
 % = 53;<br>
 ^ = 54;<br>
 & = 55;<br>
 * = 56;<br>
 ( = 57;<br>
 ) = 48;<br>
 - = 189;<br>
 _ = 189;<br>
 + = 187;<br>
 = = 187;<br>
 [ = 219;<br>
 { = 219;<br>
 ] = 221;<br>
 } = 221;<br>
 : = 186;<br>
 ; = 186;<br>
 " = 222;<br>
 ' = 222;<br>
 , = 188;<br>
 < = 188;<br>
 . = 190;<br>
 > = 190;<br>
 / = 191;<br>
 ? = 191;<br>
<i>{space}<i/> = 32;<br>
 * @version 1.0.
 * @author eMeS
 * @see lv.emes.libraries.tools.MS_KeyCodeDictionary
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MSKeyCodeDictionaryTest {
    @Test
    public void test01Symbols() {
        assertEquals(65, MS_KeyCodeDictionary.textToKeyCode("a"));
        assertEquals(65, MS_KeyCodeDictionary.textToKeyCode("A"));
        assertEquals(90, MS_KeyCodeDictionary.textToKeyCode("z"));
        assertEquals(90, MS_KeyCodeDictionary.textToKeyCode("Z"));
        assertEquals(49, MS_KeyCodeDictionary.textToKeyCode("1"));
        assertEquals(57, MS_KeyCodeDictionary.textToKeyCode("9"));
        assertEquals(48, MS_KeyCodeDictionary.textToKeyCode("0"));

        assertEquals(32, MS_KeyCodeDictionary.textToKeyCode(" "));
        assertEquals(VK_BACK_SLASH, MS_KeyCodeDictionary.textToKeyCode("|")); //todo CORRECT THIS ONE!
        assertEquals(VK_BACK_SLASH, MS_KeyCodeDictionary.textToKeyCode("\\"));
        assertEquals(192, MS_KeyCodeDictionary.textToKeyCode("~"));
        assertEquals(192, MS_KeyCodeDictionary.textToKeyCode("`"));
        assertEquals(49, MS_KeyCodeDictionary.textToKeyCode("!"));
        assertEquals(50, MS_KeyCodeDictionary.textToKeyCode("@"));
        assertEquals(51, MS_KeyCodeDictionary.textToKeyCode("#"));
        assertEquals(52, MS_KeyCodeDictionary.textToKeyCode("$"));
        assertEquals(53, MS_KeyCodeDictionary.textToKeyCode("%"));
        assertEquals(54, MS_KeyCodeDictionary.textToKeyCode("^"));
        assertEquals(55, MS_KeyCodeDictionary.textToKeyCode("&"));
        assertEquals(56, MS_KeyCodeDictionary.textToKeyCode("*"));
        assertEquals(57, MS_KeyCodeDictionary.textToKeyCode("("));
        assertEquals(48, MS_KeyCodeDictionary.textToKeyCode(")"));
        assertEquals(VK_MINUS, MS_KeyCodeDictionary.textToKeyCode("-"));
        assertEquals(VK_MINUS, MS_KeyCodeDictionary.textToKeyCode("_"));
        assertEquals(VK_EQUALS, MS_KeyCodeDictionary.textToKeyCode("+"));
        assertEquals(VK_EQUALS, MS_KeyCodeDictionary.textToKeyCode("="));
        assertEquals(VK_OPEN_BRACKET, MS_KeyCodeDictionary.textToKeyCode("["));
        assertEquals(VK_OPEN_BRACKET, MS_KeyCodeDictionary.textToKeyCode("{"));
        assertEquals(VK_CLOSE_BRACKET, MS_KeyCodeDictionary.textToKeyCode("]"));
        assertEquals(VK_CLOSE_BRACKET, MS_KeyCodeDictionary.textToKeyCode("}"));
        assertEquals(VK_SEMICOLON, MS_KeyCodeDictionary.textToKeyCode(":"));
        assertEquals(VK_SEMICOLON, MS_KeyCodeDictionary.textToKeyCode(";"));
        assertEquals(222, MS_KeyCodeDictionary.textToKeyCode("\""));
        assertEquals(222, MS_KeyCodeDictionary.textToKeyCode("'"));
        assertEquals(VK_COMMA, MS_KeyCodeDictionary.textToKeyCode(","));
        assertEquals(VK_COMMA, MS_KeyCodeDictionary.textToKeyCode("<"));
        assertEquals(VK_PERIOD, MS_KeyCodeDictionary.textToKeyCode(">"));
        assertEquals(VK_PERIOD, MS_KeyCodeDictionary.textToKeyCode("."));
        assertEquals(VK_SLASH, MS_KeyCodeDictionary.textToKeyCode("/"));
        assertEquals(VK_SLASH, MS_KeyCodeDictionary.textToKeyCode("?"));
    }

    @Test
    public void test02OtherKeys() {
        assertEquals(VK_CONTROL, MS_KeyCodeDictionary.textToKeyCode("Ctrl"));
        assertEquals(VK_CONTROL, MS_KeyCodeDictionary.textToKeyCode("CTRL"));
        assertEquals(VK_ALT, MS_KeyCodeDictionary.textToKeyCode("ALT"));
        assertEquals(VK_SHIFT, MS_KeyCodeDictionary.textToKeyCode("SHIFT"));
        assertEquals(VK_DELETE, MS_KeyCodeDictionary.textToKeyCode("DEL"));
        assertEquals(VK_DELETE, MS_KeyCodeDictionary.textToKeyCode("DELETE"));
        assertEquals(VK_INSERT, MS_KeyCodeDictionary.textToKeyCode("INS"));
        assertEquals(VK_INSERT, MS_KeyCodeDictionary.textToKeyCode("INSERT"));
        assertEquals(VK_HOME, MS_KeyCodeDictionary.textToKeyCode("HOME"));
        assertEquals(VK_END, MS_KeyCodeDictionary.textToKeyCode("END"));
        assertEquals(VK_PAGE_UP, MS_KeyCodeDictionary.textToKeyCode("PGUP"));
        assertEquals(VK_PAGE_UP, MS_KeyCodeDictionary.textToKeyCode("PAGEUP"));
        assertEquals(VK_PAGE_UP, MS_KeyCodeDictionary.textToKeyCode("PG_UP"));
        assertEquals(VK_PAGE_UP, MS_KeyCodeDictionary.textToKeyCode("PAGE_UP"));
        assertEquals(VK_PAGE_DOWN, MS_KeyCodeDictionary.textToKeyCode("PGDOWN"));
        assertEquals(VK_PAGE_DOWN, MS_KeyCodeDictionary.textToKeyCode("PG_DOWN"));
        assertEquals(VK_PAGE_DOWN, MS_KeyCodeDictionary.textToKeyCode("Page_DOWN"));
        assertEquals(VK_PAGE_DOWN, MS_KeyCodeDictionary.textToKeyCode("PAGEDOWN"));
        assertEquals(MS_KeyCodeDictionary.textToKeyCode("SPACE"), 32);
        assertEquals(VK_ESCAPE, MS_KeyCodeDictionary.textToKeyCode("ESC"));
        assertEquals(VK_ESCAPE, MS_KeyCodeDictionary.textToKeyCode("ESCAPE"));
        assertEquals(VK_ENTER, MS_KeyCodeDictionary.textToKeyCode("ENT"));
        assertEquals(VK_ENTER, MS_KeyCodeDictionary.textToKeyCode("ENTER"));
        assertEquals(VK_BACK_SPACE, MS_KeyCodeDictionary.textToKeyCode("BACK"));
        assertEquals(VK_BACK_SPACE, MS_KeyCodeDictionary.textToKeyCode("BCK"));
        assertEquals(VK_BACK_SPACE, MS_KeyCodeDictionary.textToKeyCode("BACKSPACE"));
        assertEquals(VK_BACK_SPACE, MS_KeyCodeDictionary.textToKeyCode("BCKS"));
        assertEquals(VK_TAB, MS_KeyCodeDictionary.textToKeyCode("TAB"));
        assertEquals(VK_CAPS_LOCK, MS_KeyCodeDictionary.textToKeyCode("CAPS"));
        assertEquals(VK_CAPS_LOCK, MS_KeyCodeDictionary.textToKeyCode("CAPSLOCK"));
        assertEquals(VK_CAPS_LOCK, MS_KeyCodeDictionary.textToKeyCode("CAPS_LOCK"));

        assertEquals(MS_KeyCodeDictionary.textToKeyCode("F1"), 112);
        assertEquals(MS_KeyCodeDictionary.textToKeyCode("F2"), 113);
        assertEquals(MS_KeyCodeDictionary.textToKeyCode("F3"), 114);
        assertEquals(MS_KeyCodeDictionary.textToKeyCode("F4"), 115);
        assertEquals(MS_KeyCodeDictionary.textToKeyCode("F5"), 116);
        assertEquals(MS_KeyCodeDictionary.textToKeyCode("F6"), 117);
        assertEquals(MS_KeyCodeDictionary.textToKeyCode("F7"), 118);
        assertEquals(MS_KeyCodeDictionary.textToKeyCode("F8"), 119);
        assertEquals(MS_KeyCodeDictionary.textToKeyCode("F9"), 120);
        assertEquals(MS_KeyCodeDictionary.textToKeyCode("F10"), 121);
        assertEquals(MS_KeyCodeDictionary.textToKeyCode("F11"), 122);
        assertEquals(MS_KeyCodeDictionary.textToKeyCode("F12"), 123);

        assertEquals(VK_CONTEXT_MENU, MS_KeyCodeDictionary.textToKeyCode("MENU"));
        assertEquals(VK_CONTEXT_MENU, MS_KeyCodeDictionary.textToKeyCode("RIGHT_MCLICK"));
        assertEquals(VK_WINDOWS, MS_KeyCodeDictionary.textToKeyCode("WIN"));
        assertEquals(VK_WINDOWS, MS_KeyCodeDictionary.textToKeyCode("WINDOWS"));

        assertEquals(VK_BACK_SLASH, MS_KeyCodeDictionary.textToKeyCode("BACKSLASH"));
        assertEquals(VK_SLASH, MS_KeyCodeDictionary.textToKeyCode("SLASH"));
        assertEquals(MS_KeyCodeDictionary.textToKeyCode("LEFT"), 37);
        assertEquals(MS_KeyCodeDictionary.textToKeyCode("UP"), 38);
        assertEquals(MS_KeyCodeDictionary.textToKeyCode("RIGHT"), 39);
        assertEquals(MS_KeyCodeDictionary.textToKeyCode("DOWN"), 40);
        assertEquals(MS_KeyCodeDictionary.textToKeyCode("NUM_LOCK"), 144);
        assertEquals(MS_KeyCodeDictionary.textToKeyCode("NUMLOCK"), 144);
    }

    @Test
    public void test03BadKeys() {
        assertEquals(0, MS_KeyCodeDictionary.textToKeyCode(" NUMLOCK "));
        assertEquals(0, MS_KeyCodeDictionary.textToKeyCode("00"));
        assertEquals(0, MS_KeyCodeDictionary.textToKeyCode("Badwords"));
        assertEquals(0, MS_KeyCodeDictionary.textToKeyCode("#)*&$*(@_)#^&&$@^"));
    }

    @Test
    public void test04KeysInCombinationWithShift() {
        //true conditions
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('!'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('@'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('#'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('$'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('%'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('^'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('&'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('*'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('('));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar(')'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('_'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('+'));

        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('|'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('~'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar(':'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('"'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('<'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('>'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('?'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('{'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('}'));

        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('A'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('B'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('C'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('D'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('E'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('F'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('G'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('H'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('I'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('J'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('K'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('L'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('M'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('N'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('O'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('P'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('R'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('S'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('T'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('U'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('V'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('Z'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('Q'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('W'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('X'));
        assertTrue(MS_KeyCodeDictionary.needToPushShiftToWriteChar('Y'));

        //false conditions
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('1'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('2'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('3'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('4'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('5'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('6'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('7'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('8'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('9'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('0'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('-'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('='));

        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('\\'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('`'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('['));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar(']'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar(';'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('\''));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar(','));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('.'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('/'));

        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('a'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('b'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('c'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('d'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('e'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('f'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('g'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('h'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('i'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('j'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('k'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('l'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('m'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('n'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('o'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('p'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('r'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('s'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('t'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('u'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('v'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('z'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('q'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('w'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('x'));
        assertFalse(MS_KeyCodeDictionary.needToPushShiftToWriteChar('y'));
    }
}
