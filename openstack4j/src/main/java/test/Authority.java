package test;


import org.junit.Before;
import org.junit.Test;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.compute.Flavor;
import org.openstack4j.model.compute.Image;
import org.openstack4j.openstack.OSFactory;

import java.util.List;

public class Authority {

    public OSClient.OSClientV3 os;
    //获得token
    @Before
    public void getToken(){
        os = OSFactory.builderV3()
                .endpoint("http://192.168.11.150:5000/v3")
                .credentials("admin", "ADMIN_PASS", Identifier.byName("default"))
                .scopeToProject(Identifier.byName("admin"), Identifier.byName("default"))
                .authenticate();
    }

    @Test
    public void login(){
        System.out.print("aaa:"+os);
    }

    @Test
    public void flavors(){
        List<? extends Flavor> flavors = os.compute().flavors().list();
        for(Flavor flavor :flavors){
            System.out.println(flavor);
        }
    }

    @Test
    public void images(){
        List<? extends Image> images = (List<? extends Image>) os.images().list();
        for(Image image:images){
            System.out.println(image);
        }
    }
}
