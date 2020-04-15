package de.relulu.LightLevelOverlay;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class LiLeOvMain implements ClientModInitializer {

	public static final Logger logger = LogManager.getLogger("lightleveloverlay");

	// keybind to toggle the overlay
	private static FabricKeyBinding toggleOverlay;
	private boolean overlayEnabled = false;

	@Override
	public void onInitializeClient() {

		System.out.println("Hello Fabric world!");

		// initialize the keybinding
		toggleOverlay = FabricKeyBinding.Builder.create(
				new Identifier("lloverlay", "toggle"),
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_O,
				"controls.lloverlay.title"
		).build();

		// creates the controls menu category for this plugin
		KeyBindingRegistry.INSTANCE.addCategory("controls.lloverlay.title");
		// register the keybind itself
		KeyBindingRegistry.INSTANCE.register(toggleOverlay);

		// implement keybindings
		ClientTickCallback.EVENT.register(e ->
		{
			if(toggleOverlay.wasPressed()) {

				overlayEnabled = !overlayEnabled;
				// just another, more meaningful debug message
				logger.info(new TranslatableText(
						overlayEnabled ? "debug.lloverlay.toggle_true" : "debug.lloverlay.toggle_false").asString());



				//Toggle message
				TranslatableText msg = new TranslatableText(
						overlayEnabled ? "msg.lloverlay.toggle_true" : "msg.lloverlay.toggle_false");
				e.player.sendMessage(msg.formatted(Formatting.YELLOW));
			}
		});

		logger.info("Light Level Overlay loaded!");

	}
}
