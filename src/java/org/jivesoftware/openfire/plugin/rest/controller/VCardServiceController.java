package org.jivesoftware.openfire.plugin.rest.controller;

import org.dom4j.Element;
import org.jivesoftware.openfire.XMPPServer;
import org.jivesoftware.openfire.container.PluginManager;
import org.jivesoftware.openfire.plugin.rest.RESTServicePlugin;
import org.jivesoftware.openfire.plugin.rest.entity.*;
import org.jivesoftware.openfire.plugin.rest.exceptions.ExceptionType;
import org.jivesoftware.openfire.plugin.rest.exceptions.ServiceException;
import org.jivesoftware.openfire.plugin.rest.utils.VCardUtils;
import org.jivesoftware.openfire.vcard.VCardManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

/**
 * The Class UserServiceController.
 */
public class VCardServiceController {
    private static Logger LOG = LoggerFactory.getLogger(VCardServiceController.class);

    /** The Constant INSTANCE. */
    public static final VCardServiceController INSTANCE = new VCardServiceController();

    /** The user manager. */
    private VCardManager vcardManager;

    /** The server. */
    private XMPPServer server;

    /**
     * Gets the single instance of VCardServiceController.
     *
     * @return single instance of VCardServiceController
     */
    public static VCardServiceController getInstance() {
        return INSTANCE;
    }

    private static final PluginManager pluginManager = XMPPServer.getInstance().getPluginManager();
    private static final RESTServicePlugin plugin = (RESTServicePlugin) pluginManager.getPlugin("restapi");

    /**
     * Instantiates a new vcard service controller.
     */
    private VCardServiceController() {
        server = XMPPServer.getInstance();
        vcardManager = server.getVCardManager();
    }

    public static void log(String logMessage) {
        if (plugin.isServiceLoggingEnabled())
            LOG.info(logMessage);
    }

    /**
     * Creates the vcard.
     *
     * @param username
     *            the username entity
     * @param vcardEntity
     *            the vcard entity
     * @throws ServiceException
     *             the service exception
     */
    public void setVCard(String username, VCardEntity vcardEntity) throws ServiceException {
        if (vcardEntity != null && !vcardEntity.getFullName().isEmpty()) {
            log("Creates a vcard for: " + vcardEntity.getFullName());
            try {
                vcardManager.setVCard(username, VCardUtils.convertVCardEntityToElement(vcardEntity));
            } catch (Exception e) {
                throw new ServiceException("Vcard manager failed to add user", vcardEntity.getFullName(),
                        ExceptionType.ILLEGAL_ARGUMENT_EXCEPTION, Response.Status.CONFLICT);
            }
        } else {
            throw new ServiceException("Could not add vcard to user",
                    "users", ExceptionType.ILLEGAL_ARGUMENT_EXCEPTION, Response.Status.BAD_REQUEST);
        }
    }

    /**
     * Delete vcard.
     *
     * @param username
     *            the username
     * @throws ServiceException
     *             the service exception
     */
    public void deleteVCard(String username) throws ServiceException {
        log("Delete the vcard for: " + username);
        vcardManager.deleteVCard(username);
    }

    /**
     * Gets the vcard entity.
     *
     * @param username
     *            the username
     * @return the vcard entity
     * @throws ServiceException
     *             the service exception
     */
    public VCardEntity getVCardEntity(String username) throws ServiceException {
        log("Get the vcard entity from user: " + username);
        return VCardUtils.convertVCardToVCardEntity(vcardManager.getVCard(username));
    }
}
