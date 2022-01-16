package fhtw.timetracker.service;

public class StateService {

    private static StateService stateService;

    private int userId;
    private int recordId;
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

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) { this.recordId = recordId; }

    public String getAuthHeader() {
        return authHeader;
    }

    public void setAuthHeader(String authHeader) {
        this.authHeader = authHeader;
    }

    public void reset() {
        userId = -1;
        recordId = -1;
        authHeader = "";
    }
}
