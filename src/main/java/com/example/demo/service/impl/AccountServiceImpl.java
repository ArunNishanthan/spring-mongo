package com.example.demo.service.impl;

import com.example.demo.model.AccountInfo;
import com.example.demo.model.RequestQueue;
import com.example.demo.repositories.AccountInfoRepository;
import com.example.demo.repositories.RequestQueueRepository;
import com.example.demo.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountInfoRepository accountInfoRepository;

    private final RequestQueueRepository requestQueueRepository;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccountInfo save(RequestQueue requestQueue) throws Exception {
        try{
            requestQueueRepository.save(requestQueue);
            var accountInfo = accountInfoRepository.findAll().get(0);

            if(requestQueue.getType().equalsIgnoreCase("C")){
                accountInfo.setBalance(accountInfo.getBalance().add(requestQueue.getAmount()));
                Thread.sleep(5000);
            }else{
                accountInfo.setBalance(accountInfo.getBalance().add(requestQueue.getAmount().negate()));
            }

            return accountInfoRepository.save(accountInfo);
        }catch (Exception exception){
            log.info(exception.getLocalizedMessage());
            exception.printStackTrace();
            throw exception;
        }

    }


    /*

    2024-01-10T00:37:41.605+08:00  INFO 20120 --- [nio-8080-exec-3] c.e.d.service.impl.AccountServiceImpl    : Command failed with error 112 (WriteConflict): 'WriteConflict error: this operation conflicted with another operation. Please retry your operation or multi-document transaction.' on server 127.0.0.1:27017. The full response is {"errorLabels": ["TransientTransactionError"], "ok": 0.0, "errmsg": "WriteConflict error: this operation conflicted with another operation. Please retry your operation or multi-document transaction.", "code": 112, "codeName": "WriteConflict", "$clusterTime": {"clusterTime": {"$timestamp": {"t": 1704818260, "i": 1}}, "signature": {"hash": {"$binary": {"base64": "AAAAAAAAAAAAAAAAAAAAAAAAAAA=", "subType": "00"}}, "keyId": 0}}, "operationTime": {"$timestamp": {"t": 1704818260, "i": 1}}}
org.springframework.data.mongodb.UncategorizedMongoDbException: Command failed with error 112 (WriteConflict): 'WriteConflict error: this operation conflicted with another operation. Please retry your operation or multi-document transaction.' on server 127.0.0.1:27017. The full response is {"errorLabels": ["TransientTransactionError"], "ok": 0.0, "errmsg": "WriteConflict error: this operation conflicted with another operation. Please retry your operation or multi-document transaction.", "code": 112, "codeName": "WriteConflict", "$clusterTime": {"clusterTime": {"$timestamp": {"t": 1704818260, "i": 1}}, "signature": {"hash": {"$binary": {"base64": "AAAAAAAAAAAAAAAAAAAAAAAAAAA=", "subType": "00"}}, "keyId": 0}}, "operationTime": {"$timestamp": {"t": 1704818260, "i": 1}}}
	at org.springframework.data.mongodb.core.MongoExceptionTranslator.translateExceptionIfPossible(MongoExceptionTranslator.java:135)
	at org.springframework.data.mongodb.core.MongoTemplate.potentiallyConvertRuntimeException(MongoTemplate.java:2993)
	at org.springframework.data.mongodb.core.MongoTemplate.execute(MongoTemplate.java:603)
	at org.springframework.data.mongodb.core.MongoTemplate.saveDocument(MongoTemplate.java:1583)
	at org.springframework.data.mongodb.core.MongoTemplate.doSave(MongoTemplate.java:1515)
	at org.springframework.data.mongodb.core.MongoTemplate.save(MongoTemplate.java:1458)
	at org.springframework.data.mongodb.repository.support.SimpleMongoRepository.save(SimpleMongoRepository.java:98)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:352)
	at org.springframework.data.repository.core.support.RepositoryMethodInvoker$RepositoryFragmentMethodInvoker.lambda$new$0(RepositoryMethodInvoker.java:277)
	at org.springframework.data.repository.core.support.RepositoryMethodInvoker.doInvoke(RepositoryMethodInvoker.java:170)
	at org.springframework.data.repository.core.support.RepositoryMethodInvoker.invoke(RepositoryMethodInvoker.java:158)
	at org.springframework.data.repository.core.support.RepositoryComposition$RepositoryFragments.invoke(RepositoryComposition.java:516)
	at org.springframework.data.repository.core.support.RepositoryComposition.invoke(RepositoryComposition.java:285)
	at org.springframework.data.repository.core.support.RepositoryFactorySupport$ImplementationMethodExecutionInterceptor.invoke(RepositoryFactorySupport.java:628)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.doInvoke(QueryExecutorMethodInterceptor.java:168)
	at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.invoke(QueryExecutorMethodInterceptor.java:143)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.data.projection.DefaultMethodInvokingMethodInterceptor.invoke(DefaultMethodInvokingMethodInterceptor.java:70)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.data.mongodb.repository.support.CrudMethodMetadataPostProcessor$CrudMethodMetadataPopulatingMethodInterceptor.invoke(CrudMethodMetadataPostProcessor.java:158)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:97)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:249)
	at jdk.proxy2/jdk.proxy2.$Proxy67.save(Unknown Source)
	at com.example.demo.service.impl.AccountServiceImpl.save(AccountServiceImpl.java:35)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:352)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:196)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:765)
	at org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:123)
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:385)
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:765)
	at org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:717)
	at com.example.demo.service.impl.AccountServiceImpl$$SpringCGLIB$$0.save(<generated>)
	at com.example.demo.controller.AccountController.saveAccountInfo(AccountController.java:20)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:262)
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:190)
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:118)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:917)
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:829)
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1089)
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:979)
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014)
	at org.springframework.web.servlet.FrameworkServlet.doPost(FrameworkServlet.java:914)
	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:590)
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885)
	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:205)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)
	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:174)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:149)
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:482)
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:115)
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:340)
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:391)
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:896)
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1744)
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)
	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
	at java.base/java.lang.Thread.run(Thread.java:833)
Caused by: com.mongodb.MongoCommandException: Command failed with error 112 (WriteConflict): 'WriteConflict error: this operation conflicted with another operation. Please retry your operation or multi-document transaction.' on server 127.0.0.1:27017. The full response is {"errorLabels": ["TransientTransactionError"], "ok": 0.0, "errmsg": "WriteConflict error: this operation conflicted with another operation. Please retry your operation or multi-document transaction.", "code": 112, "codeName": "WriteConflict", "$clusterTime": {"clusterTime": {"$timestamp": {"t": 1704818260, "i": 1}}, "signature": {"hash": {"$binary": {"base64": "AAAAAAAAAAAAAAAAAAAAAAAAAAA=", "subType": "00"}}, "keyId": 0}}, "operationTime": {"$timestamp": {"t": 1704818260, "i": 1}}}
	at com.mongodb.internal.connection.ProtocolHelper.getCommandFailureException(ProtocolHelper.java:205)
	at com.mongodb.internal.connection.InternalStreamConnection.receiveCommandMessageResponse(InternalStreamConnection.java:454)
	at com.mongodb.internal.connection.InternalStreamConnection.sendAndReceive(InternalStreamConnection.java:372)
	at com.mongodb.internal.connection.UsageTrackingInternalConnection.sendAndReceive(UsageTrackingInternalConnection.java:114)
	at com.mongodb.internal.connection.DefaultConnectionPool$PooledConnection.sendAndReceive(DefaultConnectionPool.java:765)
	at com.mongodb.internal.connection.CommandProtocolImpl.execute(CommandProtocolImpl.java:76)
	at com.mongodb.internal.connection.DefaultServer$DefaultServerProtocolExecutor.execute(DefaultServer.java:209)
	at com.mongodb.internal.connection.DefaultServerConnection.executeProtocol(DefaultServerConnection.java:115)
	at com.mongodb.internal.connection.DefaultServerConnection.command(DefaultServerConnection.java:83)
	at com.mongodb.internal.connection.DefaultServer$OperationCountTrackingConnection.command(DefaultServer.java:307)
	at com.mongodb.internal.operation.MixedBulkWriteOperation.executeCommand(MixedBulkWriteOperation.java:395)
	at com.mongodb.internal.operation.MixedBulkWriteOperation.executeBulkWriteBatch(MixedBulkWriteOperation.java:259)
	at com.mongodb.internal.operation.MixedBulkWriteOperation.lambda$execute$2(MixedBulkWriteOperation.java:203)
	at com.mongodb.internal.operation.SyncOperationHelper.lambda$withSourceAndConnection$0(SyncOperationHelper.java:127)
	at com.mongodb.internal.operation.SyncOperationHelper.withSuppliedResource(SyncOperationHelper.java:152)
	at com.mongodb.internal.operation.SyncOperationHelper.lambda$withSourceAndConnection$1(SyncOperationHelper.java:126)
	at com.mongodb.internal.operation.SyncOperationHelper.withSuppliedResource(SyncOperationHelper.java:152)
	at com.mongodb.internal.operation.SyncOperationHelper.withSourceAndConnection(SyncOperationHelper.java:125)
	at com.mongodb.internal.operation.MixedBulkWriteOperation.lambda$execute$3(MixedBulkWriteOperation.java:188)
	at com.mongodb.internal.operation.MixedBulkWriteOperation.lambda$decorateWriteWithRetries$0(MixedBulkWriteOperation.java:146)
	at com.mongodb.internal.async.function.RetryingSyncSupplier.get(RetryingSyncSupplier.java:67)
	at com.mongodb.internal.operation.MixedBulkWriteOperation.execute(MixedBulkWriteOperation.java:207)
	at com.mongodb.internal.operation.MixedBulkWriteOperation.execute(MixedBulkWriteOperation.java:77)
	at com.mongodb.client.internal.MongoClientDelegate$DelegateOperationExecutor.execute(MongoClientDelegate.java:173)
	at com.mongodb.client.internal.MongoCollectionImpl.executeSingleWriteRequest(MongoCollectionImpl.java:1085)
	at com.mongodb.client.internal.MongoCollectionImpl.executeReplaceOne(MongoCollectionImpl.java:575)
	at com.mongodb.client.internal.MongoCollectionImpl.replaceOne(MongoCollectionImpl.java:570)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at org.springframework.util.ReflectionUtils.invokeMethod(ReflectionUtils.java:281)
	at org.springframework.data.mongodb.SessionAwareMethodInterceptor.invoke(SessionAwareMethodInterceptor.java:119)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:184)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:249)
	at jdk.proxy2/jdk.proxy2.$Proxy84.replaceOne(Unknown Source)
	at org.springframework.data.mongodb.core.MongoTemplate.lambda$saveDocument$19(MongoTemplate.java:1617)
	at org.springframework.data.mongodb.core.MongoTemplate.execute(MongoTemplate.java:601)
	... 94 more
     */
}
