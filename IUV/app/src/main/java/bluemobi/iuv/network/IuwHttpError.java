package bluemobi.iuv.network;
/**
 * 
 * @author wangzhijun
 *
 */
public interface IuwHttpError {
	/**
     * @Title: translateToCn
     * @Description: 错误释类
     * @param @param errorMsg 英语错误信息
     * @param @return
     * @return String 返回类型
     * @throws
     */
	public String translateErrorToCn(int errorCode);
}
