package service;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.ws.*;
import javax.xml.*;
import javax.jws.*;
import java.util.*;

@ApplicationPath("/resources")
public class RESTApplication extends Application
{
    @Override
    public Set<Class<?>> getClasses()
    {
        Set<Class<?>> rest = new HashSet<Class<?>>();
        rest.add(LAMSService.class);
        return rest;
    }
}