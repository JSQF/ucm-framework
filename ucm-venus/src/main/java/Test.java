import com.meidusa.venus.client.simple.SimpleServiceFactory;
import com.saic.ebiz.sso.service.api.ISSOTokenService;
import com.saic.ebiz.sso.service.constant.Constant;

/**
 * Created by huawei on 12/29/15.
 */
public class Test {
    public static void main(String[] args) {
        SimpleServiceFactory ssf = new SimpleServiceFactory("10.32.188.61", 16800);
        ISSOTokenService issoTokenService = ssf.getService(ISSOTokenService.class);
        System.out.println(issoTokenService.createToken("1", Constant.ClientType.APP));;
    }

}
