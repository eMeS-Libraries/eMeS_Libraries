package lv.emes.libraries.tools.platform.windows;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import lv.emes.libraries.utilities.MS_StringUtils;

import static com.sun.jna.platform.win32.WinUser.*;

/**
 * Uses Windows User32 API to get application windows and perform hiding and showing of those windows.
 * <p>Static methods:
 * <ul>
 * <li>getWindowHandle</li>
 * <li>applicationIsRunning</li>
 * <li>showApplicationWindow</li>
 * <li>hideApplicationWindow</li>
 * </ul>
 * @author eMeS
 * @version 1.0.
 */
public class MS_ApplicationWindow {
    /**
     * Finds window handle for application with given title or name.
     * <br><u>WARNING</u>: Windows only function.
     *
     * @param appName name of application that can be found in task manager.
     * @return OS window handle.
     */
    public static HWND getWindowHandle(String appName) {
        HWND hWndTemp;
        //try to find exact match
        hWndTemp = User32.INSTANCE.FindWindow(null, appName);
        if (hWndTemp == null) { //if failed to find match lets check all the open windows
            appName = appName.toUpperCase();
            final int TEXT_LEN = 255;
            char[] cTitletemp = new char[TEXT_LEN];
            String sTitleTemp;

            hWndTemp = User32.INSTANCE.FindWindow(null, null);
            while (hWndTemp != null) {
                User32.INSTANCE.GetWindowText(hWndTemp, cTitletemp, TEXT_LEN);
                sTitleTemp = new String(cTitletemp).toUpperCase();
                if (MS_StringUtils.textContains(sTitleTemp, appName))
                    break;
                //if didn't found a match, lets go to next window
                hWndTemp = User32.INSTANCE.GetWindow(hWndTemp, new WinDef.DWORD(GW_HWNDNEXT));
            }
        }
        return hWndTemp;
    }

    /**
     * Tests, whether some application is running and can be found in task manager.
     * This method uses a window handle.
     * <br><u>WARNING</u>: Windows only function.
     *
     * @param appName name of application that can be found in Windows task manager.
     * @return true if application is running and can be found.
     */
    public static Boolean applicationIsRunning(String appName) {
        WinDef.HWND hwnd = getWindowHandle(appName);
        return hwnd != null;
    }

    /**
     * Forces application window to move to foreground.
     * This method uses a window handle.
     * <br><u>WARNING</u>: Windows only function.
     *
     * @param appName name of application that can be found in task manager.
     * @return true if action successfully finished.
     */
    public static Boolean showApplicationWindow(String appName) {
        WinDef.HWND hwnd = getWindowHandle(appName);
        //ShowWindow function
        //https://msdn.microsoft.com/en-us/library/windows/desktop/ms633548(v=vs.85).aspx
        return
                User32.INSTANCE.ShowWindow(hwnd, SW_SHOW)  // Make the window visible if it was hidden
                        &&
                        User32.INSTANCE.ShowWindow(hwnd, SW_SHOWNA)
                        &&
                        User32.INSTANCE.SetForegroundWindow(hwnd)
                ;
    }

    /**
     * Forces application window to move to background (hide).
     * This method uses a window handle.
     * <br><u>WARNING</u>: Windows only function.
     *
     * @param appName name of application that can be found in task manager.
     * @return true if action successfully finished.
     */
    public static Boolean hideApplicationWindow(String appName) {
        WinDef.HWND hwnd = getWindowHandle(appName);
        //ShowWindow function
        //https://msdn.microsoft.com/en-us/library/windows/desktop/ms633548(v=vs.85).aspx
        return User32.INSTANCE.ShowWindow(hwnd, SW_MINIMIZE);
    }
}
