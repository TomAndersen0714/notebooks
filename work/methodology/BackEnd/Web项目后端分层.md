# Web项目后端分层


service、controller、dao

分层是抽象，是设置边界，是便于人脑理解，可以减小单次思维的复杂度。人脑的缓存是有限的，当复杂度上升到一定程度之后，人脑就无法一次性理解全局。


controller只是控制调用逻辑，service是为了提供服务，DAO是为了提供DB对象的CRUD服务


controller层类命名应该是动词+名词，service层类命名应该是名词，DAO层类命名应该是名词
