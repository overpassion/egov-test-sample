package egovframework.example.interceptor;

import java.sql.Statement;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

@Intercepts({@Signature(type=StatementHandler.class, method="update", args={Statement.class})})
public class UpdateLogInterceptor implements Interceptor{
    
	//@Resource(name="logMapper")
	//private InterceptorLogMapper logMapper;
	@Resource(name="logDAO")
	private InterceptorLogDAO logDAO;
	
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler handler = (StatementHandler)invocation.getTarget();
        
        // 쿼리
        String sql = handler.getBoundSql().getSql();
        System.out.println("===>>> LOG =================>");
        System.out.println("===>>> sql : "+sql);
         
        // 쿼리실행시 맵핑되는 파라메터들
        String param = handler.getParameterHandler().getParameterObject()!=null ?
                             handler.getParameterHandler().getParameterObject().toString() : "";
        System.out.println("===>>> param : "+param); 
        // DB에다 로그 insert
        /////////////////
        ////////////////
        InterceptorLogVO logVO = new InterceptorLogVO();
        logVO.setId("");
        logVO.setQuery(sql);
        //logMapper.insertLog(logVO);
        logDAO.insertLog(logVO);
        
        return invocation.proceed();
    }
 
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
 
    @Override
    public void setProperties(Properties properties) {
    }
}

