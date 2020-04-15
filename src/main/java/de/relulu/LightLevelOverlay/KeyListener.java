package de.relulu.LightLevelOverlay;

import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

/**
 * Supposed to handle all keyboard interactions regarding this plugin.
 */
public class KeyListener {

    // key to actually activate and/or deactivate the overlay
    private static FabricKeyBinding toggleOverlay;
    private static KeyListener kl;

    /**
     * KeyListener constructor
     */
    public KeyListener() {
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
    }

    /**
     * Returns the KeyListener instance and creates it if nonexistent.
     * @return
     */
    public static KeyListener getInstance() {
        if(KeyListener.kl == null) {
            KeyListener.kl = new KeyListener();
        }
        return KeyListener.kl;
    }

    /*
    public boolean isActivated() {

        return false;
    }*/

}
