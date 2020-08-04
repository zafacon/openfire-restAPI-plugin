package org.jivesoftware.openfire.plugin.rest.utils;

import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.tree.DefaultElement;
import org.dom4j.tree.DefaultNamespace;
import org.dom4j.tree.DefaultText;
import org.jivesoftware.openfire.plugin.rest.entity.VCardEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * The Class VCardUtils.
 */
public class VCardUtils {

    /**
     * Instantiates a new vcard utils.
     */
    private VCardUtils() {
        throw new AssertionError();
    }

    /**
     * Convert vcard to vcard entity.
     *
     * @param vcard
     *            the vcard
     * @return the vcard entity
     */
    public static VCardEntity convertVCardToVCardEntity(Element vcard) {
        return new VCardEntity(
            vcard.elementText("FN"),
            vcard.elementText("NICKNAME"),
            vcard.elementText("ROLE"),
            vcard.element("EMAIL").elementText("USERID")
        );
    }

    /**
     * Convert vcard entity to vcard element.
     *
     * @param vcardEntity
     *            the vcard entity
     * @return the vcard element
     */
    public static Element convertVCardEntityToElement(VCardEntity vcardEntity) {

        Element vcard = new DefaultElement("vCard", new DefaultNamespace("","vcard-temp"));

        Element fullName = new DefaultElement("FN", null);
        fullName.addText(vcardEntity.getFullName());

        Element nickname = new DefaultElement("NICKNAME", null);
        nickname.addText(vcardEntity.getNickname());

        Element role = new DefaultElement("ROLE", null);
        role.addText(vcardEntity.getRole());

        Element email = new DefaultElement("EMAIL", null);
        Element userid = new DefaultElement("USERID", null);

        userid.addText(vcardEntity.getEmail());

        ArrayList<Node> emailContent = new ArrayList<Node>();
        emailContent.add(userid);
        email.setContent(emailContent);

        ArrayList<Node> vcardContent = new ArrayList<Node>();
        vcardContent.add(fullName);
        vcardContent.add(nickname);
        vcardContent.add(role);
        vcardContent.add(email);

        vcard.setContent(vcardContent);

        return vcard;

    }
}
