package com.workoutapp.constant;

public class ErrorMessage {
    public static final String USER_NOT_FOUND_BY_ID = "The user does not exist by this id: ";
    public static final String USER_NOT_FOUND_BY_EMAIL = "The user does not exist by this email: ";
    public static final String USER_CANT_UPDATE_THEMSELVES = "User can't update themselves";
    public static final String IMPOSSIBLE_UPDATE_USER_STATUS = "Impossible to update status of admin or moderator";
    public static final String PROFILE_PICTURE_NOT_FOUND_BY_ID = "Profile picture not found by id : ";
    public static final String IMAGE_EXISTS = "Image should be download, PNG or JPEG ";
    public static final String INVALID_URI = "The string could not be parsed as a URI reference.";
    public static final String MALFORMED_URL = "Malformed URL. The string could not be parsed.";
    public static final String USER_CANNOT_ADD_SAME_SOCIAL_NETWORK_LINKS =
            "User cannot add the same social network links";
    public static final String USER_DEACTIVATED = "User is deactivated";
    public static final String NO_ANY_EMAIL_TO_VERIFY_BY_THIS_TOKEN = "No any email to verify by this token";
    public static final String EMAIL_TOKEN_EXPIRED = "User late with verify. Token is invalid.";
    public static final String PASSWORD_RESTORE_LINK_ALREADY_SENT =
            "Password restore link already sent, please check your email: ";
    public static final String REFRESH_TOKEN_NOT_VALID = "Refresh token not valid!";
    public static final String BAD_PASSWORD = "Bad password";
    public static final String USER_ALREADY_REGISTERED_WITH_THIS_EMAIL = "User with this email is already registered";
    public static final String PASSWORDS_DO_NOT_MATCH = "The passwords don't match";
    public static final String PASSWORD_DOES_NOT_MATCH = "The password doesn't match";
    public static final String SELECT_CORRECT_LANGUAGE = "Select correct language: 'en', 'ua' or 'ru'";
    public static final String TOKEN_FOR_RESTORE_IS_INVALID = "Token is null or it doesn't exist.";
    public static final String LANGUAGE_NOT_FOUND_BY_ID = "The language does not exist by this id: ";
    public static final String USER_DEACTIVATION_REASON_IS_EMPTY = "The User deactivation reasons list is empty";
    public static final String USER_ALREADY_HAS_PASSWORD = "User already has password";
    public static final String LINK_IS_NO_ACTIVE = "This link is no longer active";
    public static final String USER_BLOCKED = "User is blocked";
    public static final String USER_CREATED = "User is not activated";
    public static final String USER_EMAIL_IS_NOT_VERIFIED = "The user's email address has not been verified.";

    public static final String USER_DOESNT_HAVE_ACCESS_TO_DATA = "User doesn't have acces to this data.";

    public static final String INCORRECT_PASSWORD = "Incorrect password";

    private ErrorMessage() {
    }
}
