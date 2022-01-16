package fhtw.timetracker.service;

public class StateService {

    private static StateService stateService;

    private int userId;
    private String authHeader = "";

    public static StateService getInstance() {
        if (stateService == null) {
            stateService = new StateService();
        }

        return stateService;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAuthHeader() {
        return authHeader;
    }

    public void setAuthHeader(String authHeader) {
        this.authHeader = authHeader;
    }

    public void reset() {
        userId = -1;
        authHeader = "";
    }
}
