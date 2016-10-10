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

public class EmployeeManagementPortProxy{

    protected Descriptor _descriptor;

    public class Descriptor {
        private com.ibm.achievement.service.EmployeeManagementService _service = null;
        private com.ibm.achievement.service.EmployeeManagement _proxy = null;
        private Dispatch<Source> _dispatch = null;
        private boolean _useJNDIOnly = false;

        public Descriptor() {
            init();
        }

        public Descriptor(URL wsdlLocation, QName serviceName) {
            _service = new com.ibm.achievement.service.EmployeeManagementService(wsdlLocation, serviceName);
            initCommon();
        }

        public void init() {
            _service = null;
            _proxy = null;
            _dispatch = null;
            try
            {
                InitialContext ctx = new InitialContext();
                _service = (com.ibm.achievement.service.EmployeeManagementService)ctx.lookup("java:comp/env/service/EmployeeManagementService");
            }
            catch (NamingException e)
            {
                if ("true".equalsIgnoreCase(System.getProperty("DEBUG_PROXY"))) {
                    System.out.println("JNDI lookup failure: javax.naming.NamingException: " + e.getMessage());
                    e.printStackTrace(System.out);
                }
            }

            if (_service == null && !_useJNDIOnly)
                _service = new com.ibm.achievement.service.EmployeeManagementService();
            initCommon();
        }

        private void initCommon() {
            _proxy = _service.getEmployeeManagementPort();
        }

        public com.ibm.achievement.service.EmployeeManagement getProxy() {
            return _proxy;
        }

        public void useJNDIOnly(boolean useJNDIOnly) {
            _useJNDIOnly = useJNDIOnly;
            init();
        }

        public Dispatch<Source> getDispatch() {
            if (_dispatch == null ) {
                QName portQName = new QName("http://service.achievement.ibm.com/", "EmployeeManagementPort");
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

    public EmployeeManagementPortProxy() {
        _descriptor = new Descriptor();
        _descriptor.setMTOMEnabled(true);
    }

    public EmployeeManagementPortProxy(URL wsdlLocation, QName serviceName) {
        _descriptor = new Descriptor(wsdlLocation, serviceName);
        _descriptor.setMTOMEnabled(true);
    }

    public Descriptor _getDescriptor() {
        return _descriptor;
    }

    public List<EmployeeVO> findEmployeesByManagerFlag() throws AchievementTrackerException_Exception {
        return _getDescriptor().getProxy().findEmployeesByManagerFlag();
    }

    public EmployeeVO findEmployeesByEmailID(String emailID) throws AchievementTrackerException_Exception {
        return _getDescriptor().getProxy().findEmployeesByEmailID(emailID);
    }

    public EmployeeVO findEmployeesByID(String empID) throws AchievementTrackerException_Exception {
        return _getDescriptor().getProxy().findEmployeesByID(empID);
    }

    public List<EmployeeVO> findEmployees(String emailID, String firstName, String lastName, String managerFlag) throws AchievementTrackerException_Exception {
        return _getDescriptor().getProxy().findEmployees(emailID,firstName,lastName,managerFlag);
    }

}