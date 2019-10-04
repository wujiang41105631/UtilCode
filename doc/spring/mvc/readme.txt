1。servlet3.0 时，不需要web.xml中。servelt 直接继承HttpServlet，通过@WebServlet添加Servlet
    同样的 @WebFilter ，@WebListener 来代替xml中的配置。或者通过spi也可以向servletContext注册servlet，listener，filter
