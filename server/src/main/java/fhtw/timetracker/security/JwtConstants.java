package fhtw.timetracker.security;

public class JwtConstants {
    public static String SIGN_UP_URL = "/users";
    public static String SECRET = "DATSECR3T";
    public static Long EXPIRATION_TIME = 864000000L;
    public static String TOKEN_PREFIX = "Bearer ";
    public static String HEADER_STRING = "Authorization";

}
