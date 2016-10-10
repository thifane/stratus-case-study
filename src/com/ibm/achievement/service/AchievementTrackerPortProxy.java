package com.ibm.achievement.service;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import java.util.List;

public class AchievementTrackerPortProxy{

    protected Descriptor _descriptor;

    public class Descriptor {
        private com.ibm.achievement.service.AchievementTrackerService _service = null;
        private com.ibm.achievement.service.AchievementTracker _proxy = null;
        private Dispatch<Source> _dispatch = null;
        private boolean _useJNDIOnly = false;

        public Descriptor() {
            init();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new com.ibm.achievement.service.AchievementTrackerService(wsdlLocation, serviceName);
            initCommon();
        }

        public void init() {
            _service = null;
            _proxy = null;
            _dispatch = null;
            try
            {
                InitialContext ctx = new InitialContext();
                _service = (com.ibm.achievement.service.AchievementTrackerService)ctx.lookup("java:comp/env/service/AchievementTrackerService");
            }
            catch (NamingException e)
            {
                if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
                    System.out.println("JNDI lookup failure: javax.naming.NamingException: " + e.getMessage());
                    e.printStackTrace(System.out);
                }
            }

            if (_service == null && !_useJNDIOnly)
                _service = new com.ibm.achievement.service.AchievementTrackerService();
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getAchievementTrackerPort();
        }

        public com.ibm.achievement.service.AchievementTracker getProxy() {
            return _proxy;
        }

        public void useJNDIOnly(boolean useJNDIOnly) {
            _useJNDIOnly = useJNDIOnly;
            init();
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null ) {
                QName portQName = new QName("http://service.achievement.ibm.com/", "AchievementTrackerPort");
                _dispatch = _service.createDispatch(portQName, Source.class, Service.Mode.MESSAGE);

                String proxyEndpointUrl = getEndpoint();
                BindingProvider bp = (BindingProvider) _dispatch;
                String dispatchEndpointUrl = (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
                if (!dispatchEndpointUrl.equals(proxyEndpointUrl))
                    bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, proxyEndpointUrl);
            }
            return _dispatch;
        }

        public String getEndpoint() {
            BindingProvider bp = (BindingProvider) _proxy;
            return (String) bp.getRequestContext().get(BindingProvider.ENDPOINT_ADDRESS_PROPERTY);
        }

        public void setEndpoint(String endpointUrl) {
            BindingProvider bp = (BindingProvider) _proxy;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);

            if (_dispatch != null ) {
                bp = (BindingProvider) _dispatch;
                bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);
            }
        }

        public void setMTOMEnabled(boolean enable) {
            SOAPBinding binding = (SOAPBinding) ((BindingProvider) _proxy).getBinding();
            binding.setMTOMEnabled(enable);
        }
    }

    public AchievementTrackerPortProxy() {
        _descriptor = new Descriptor();
        _descriptor.setMTOMEnabled(true);
    }

    public AchievementTrackerPortProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
        _descriptor.setMTOMEnabled(true);
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }

    public List<AchievementTypesVO> findAchievementTypes() throws AchievementTrackerException_Exception {
        return _getDescriptor().getProxy().findAchievementTypes();
    }

    public AchievementTypesVO findAchievementType(String typeId) throws AchievementTrackerException_Exception {
        return _getDescriptor().getProxy().findAchievementType(typeId);
    }

    public List<AchievementCatgryVO> findAchievementCategories() throws AchievementTrackerException_Exception {
        return _getDescriptor().getProxy().findAchievementCategories();
    }

    public boolean saveAchievement(AchievementVO achievementVO) throws AchievementTrackerException_Exception {
        return _getDescriptor().getProxy().saveAchievement(achievementVO);
    }

    public List<AchievementVO> findAchievementByEmpID(String employeeId) throws AchievementTrackerException_Exception {
        return _getDescriptor().getProxy().findAchievementByEmpID(employeeId);
    }

    public List<AchievementVO> findAchievementByApproverID(String approverId) throws AchievementTrackerException_Exception {
        return _getDescriptor().getProxy().findAchievementByApproverID(approverId);
    }

    public AchievementVO findAchievementById(int achievementId) throws AchievementTrackerException_Exception {
        return _getDescriptor().getProxy().findAchievementById(achievementId);
    }

    public boolean approveAchievement(String achievementId, String status, String category, int pointValue, String approverComment) throws AchievementTrackerException_Exception {
        return _getDescriptor().getProxy().approveAchievement(achievementId,status,category,pointValue,approverComment);
    }

    public AchievementCountVO findAchievementSummery(String employeeId) throws AchievementTrackerException_Exception {
        return _getDescriptor().getProxy().findAchievementSummery(employeeId);
    }

}