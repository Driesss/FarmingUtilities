package be.driesstelten.FarmingUtilities.client.handler;

import be.driesstelten.FarmingUtilities.client.settings.Keybindings;
import be.driesstelten.FarmingUtilities.reference.Key;
import be.driesstelten.FarmingUtilities.utility.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class KeyInputEventHandler {
	
	
	private static Key getPressedKeybinding() {
		
		return null;
	
	}
	
	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
		
		//LogHelper.info(getPressedKeybinding());
		
	}

}
