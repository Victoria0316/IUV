package bluemobi.iuv.network.response;


import bluemobi.iuv.network.IuwHttpResponse;
import bluemobi.iuv.network.model.UserInfo;

public class LoginResponse extends IuwHttpResponse
{
    private UserInfo data;

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }
}
