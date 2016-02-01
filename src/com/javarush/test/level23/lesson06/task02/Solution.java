package com.javarush.test.level23.lesson06.task02;

/* Рефакторинг
Отрефакторите класс Solution: вынесите все константы в public вложенный(nested) класс Constants.
Запретите наследоваться от Constants.
*/
public class Solution {

    public static final class Constants {

        public static final String SERVER_NOT_ACCESSIBLE_EXCEPTION = "Server is not accessible for now.";
        public static final String UNAUTHORIZED_USER_EXCEPTION = "User is not authorized.";
        public static final String BANNED_USER_EXCEPTION = "User is banned.";
        public static final String RESTRICTION_EXCEPTION = "Access is denied.";
    }

    public class ServerNotAccessibleException extends Exception {
        public ServerNotAccessibleException() {
            super(Constants.SERVER_NOT_ACCESSIBLE_EXCEPTION);
        }

        public ServerNotAccessibleException(Throwable cause) {
            super(Constants.SERVER_NOT_ACCESSIBLE_EXCEPTION, cause);
        }
    }

    public class UnauthorizedUserException extends Exception {
        public UnauthorizedUserException() {
            super(Constants.UNAUTHORIZED_USER_EXCEPTION);
        }

        public UnauthorizedUserException(Throwable cause) {
            super(Constants.UNAUTHORIZED_USER_EXCEPTION, cause);
        }
    }

    public class BannedUserException extends Exception {
        public BannedUserException() {
            super(Constants.BANNED_USER_EXCEPTION);
        }

        public BannedUserException(Throwable cause) {
            super(Constants.BANNED_USER_EXCEPTION, cause);
        }
    }

    public class RestrictionException extends Exception {
        public RestrictionException() {
            super(Constants.RESTRICTION_EXCEPTION);
        }

        public RestrictionException(Throwable cause) {
            super(Constants.RESTRICTION_EXCEPTION, cause);
        }
    }
}
