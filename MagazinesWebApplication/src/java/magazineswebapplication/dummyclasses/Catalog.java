/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.dummyclasses;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author zofia
 */
public class Catalog {
    private String magazineId;
    private String nameTag;
    private String type;

    public Catalog(String magazineId, String nameTag, String type) {
        this.magazineId = magazineId;
        this.nameTag = nameTag;
        this.type = type;
    }

    public String getMagazineName() {
        return magazineId;
    }

    public void setMagazineName(String magazineName) {
        this.magazineId = magazineName;
    }

    public String getNameTag() {
        return nameTag;
    }

    public void setNameTag(String nameTag) {
        this.nameTag = nameTag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
