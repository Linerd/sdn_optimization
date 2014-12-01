/**
 * 
 */
package edu.thu.we.filter;

import java.util.Collection;
import java.util.Map;

import org.openflow.protocol.OFFlowMod;
import org.openflow.protocol.OFMessage;
import org.openflow.protocol.OFType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.floodlightcontroller.core.FloodlightContext;
import net.floodlightcontroller.core.IFloodlightProviderService;
import net.floodlightcontroller.core.IOFMessageListener;
import net.floodlightcontroller.core.IOFSwitch;
import net.floodlightcontroller.core.module.FloodlightModuleContext;
import net.floodlightcontroller.core.module.FloodlightModuleException;
import net.floodlightcontroller.core.module.IFloodlightModule;
import net.floodlightcontroller.core.module.IFloodlightService;
import net.floodlightcontroller.counter.ICounterStoreService;

/**
 * @author yikai
 *
 */
public class WEFlowModListener implements IFloodlightModule, IOFMessageListener {

	
	protected IFloodlightProviderService floodlightProvider;
	protected ICounterStoreService counterStore;
	protected Logger logger;
	
	/* (non-Javadoc)
	 * @see net.floodlightcontroller.core.IListener#getName()
	 */
	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	/* (non-Javadoc)
	 * @see net.floodlightcontroller.core.IListener#isCallbackOrderingPrereq(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean isCallbackOrderingPrereq(OFType type, String name) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see net.floodlightcontroller.core.IListener#isCallbackOrderingPostreq(java.lang.Object, java.lang.String)
	 */
	@Override
	public boolean isCallbackOrderingPostreq(OFType type, String name) {
		return type.equals(OFType.FLOW_MOD);
	}

	/* (non-Javadoc)
	 * @see net.floodlightcontroller.core.IOFMessageListener#receive(net.floodlightcontroller.core.IOFSwitch, org.openflow.protocol.OFMessage, net.floodlightcontroller.core.FloodlightContext)
	 */
	@Override
	public net.floodlightcontroller.core.IListener.Command receive(
			IOFSwitch sw, OFMessage msg, FloodlightContext cntx) {
		if(msg.getType()==OFType.FLOW_MOD){
			System.out.println("-----new flow mod-----");
			System.out.println((OFFlowMod)msg);
			return Command.CONTINUE;
		}
		else
			return Command.CONTINUE;
	}

	/* (non-Javadoc)
	 * @see net.floodlightcontroller.core.module.IFloodlightModule#getModuleServices()
	 */
	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleServices() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.floodlightcontroller.core.module.IFloodlightModule#getServiceImpls()
	 */
	@Override
	public Map<Class<? extends IFloodlightService>, IFloodlightService> getServiceImpls() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.floodlightcontroller.core.module.IFloodlightModule#getModuleDependencies()
	 */
	@Override
	public Collection<Class<? extends IFloodlightService>> getModuleDependencies() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.floodlightcontroller.core.module.IFloodlightModule#init(net.floodlightcontroller.core.module.FloodlightModuleContext)
	 */
	@Override
	public void init(FloodlightModuleContext context)
			throws FloodlightModuleException {
		floodlightProvider =
                context.getServiceImpl(IFloodlightProviderService.class);
        counterStore =
                context.getServiceImpl(ICounterStoreService.class);
        logger = LoggerFactory.getLogger(WEFlowModListener.class);

	}

	/* (non-Javadoc)
	 * @see net.floodlightcontroller.core.module.IFloodlightModule#startUp(net.floodlightcontroller.core.module.FloodlightModuleContext)
	 */
	@Override
	public void startUp(FloodlightModuleContext context) {
		floodlightProvider.addOFMessageListener(OFType.FLOW_MOD, this);

	}

}
