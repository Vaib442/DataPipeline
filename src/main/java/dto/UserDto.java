package dto;

public class UserDto {
    private String userId;
    private String userAction;

    private boolean isLocationChanged;

    public UserDto(String userId, String userAction) {
        this.userId = userId;
        this.userAction = userAction;
    }

    public UserDto(String userId, String userAction, boolean isLocationChanged) {
        this.userId = userId;
        this.userAction = userAction;
        this.isLocationChanged = isLocationChanged;
    }


    public UserDto() {
    }

    public boolean isLocationChanged() {
        return isLocationChanged;
    }

    public void setLocationChanged(boolean locationChanged) {
        isLocationChanged = locationChanged;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAction() {
        return userAction;
    }

    public void setUserAction(String userAction) {
        this.userAction = userAction;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId='" + userId + '\'' +
                ", userAction='" + userAction + '\'' +
                ", isLocationChanged=" + isLocationChanged +
                '}';
    }
}
