package de.relulu.LightLevelOverlay;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Style;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class LiLeOvMain implements ModInitializer {

	public static final Logger logger = LogManager.getLogger("lightleveloverlay");

	// keybind to toggle the overlay
	private static FabricKeyBinding toggleOverlay;
	private boolean overlayEnabled = false;

	@Override
	public void onInitialize() {

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

				// just a debug message
				System.out.println("was pressed!");

				// just another, more meaningful debug message
				logger.info(new TranslatableText(
						overlayEnabled ? "debug.lloverlay.toggle_true" : "debug.lloverlay.toggle_false").asString());



				//Toggle message
				TranslatableText msg = new TranslatableText(
						overlayEnabled ? "msg.lloverlay.toggle_true" : "msg.lloverlay.toggle_false");
				msg.formatted(Formatting.YELLOW);
				e.player.sendMessage(msg);
			}
		});

		logger.info("Light Level Overlay loaded!");

	}

	//public static KeyListener kl = KeyListener.getInstance();

	/*
	@Override
	public void onInitializeClient() {

	}*/


}
