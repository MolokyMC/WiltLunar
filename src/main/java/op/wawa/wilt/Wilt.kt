package op.wawa.wilt

import op.wawa.wilt.viaforge.ViaForge
import net.minecraft.client.Minecraft
import op.wawa.wilt.gui.alt.AltManager
import op.wawa.wilt.viaforge.gui.AsyncVersionSlider
import op.wawa.wilt.module.ModuleManager
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.lwjgl.opengl.Display
import java.io.File

object Wilt {
    const val MOD_NAME = "WiltLunar"
    const val MOD_VERSION = "v1.1"
    const val MOD_OWNER = "MolokyMC-Team"
    const val MOD_DEVELOPERS = "WaWa"

    @JvmStatic
    val logger: Logger = LogManager.getLogger("WiltLunar")

    var asyncSlider: AsyncVersionSlider? = null

    @JvmStatic
    val dir = File(Minecraft.getMinecraft().mcDataDir, "WiltLunar")

    lateinit var moduleManager: ModuleManager
    lateinit var altManager: AltManager

    fun startGame(){
        val l = System.currentTimeMillis()
        logger.info("Loading $MOD_NAME $MOD_VERSION, By $MOD_OWNER, Developer $MOD_DEVELOPERS")
        Display.setTitle(Display.getTitle() + " | $MOD_NAME $MOD_VERSION - By $MOD_OWNER")

        moduleManager = ModuleManager()
        moduleManager.loadModules()
        logger.info("Modules Loaded.")

        altManager = AltManager()
        logger.info("AltManager Loaded.")

        ViaForge.initViaVersion()
        asyncSlider = AsyncVersionSlider(-1, 5, 5, 110, 20)
        logger.info("ViaLunar Loaded.")
        //ViaMCP.getInstance().version = 340

        logger.info("All Loaded. (${System.currentTimeMillis() - l} ms)")
    }
}