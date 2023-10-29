package op.wawa.wilt

import net.minecraft.client.Minecraft
import op.wawa.wilt.gui.alt.AltManager
import op.wawa.wilt.module.ModuleManager
import org.apache.logging.log4j.LogManager
import org.lwjgl.opengl.Display
import viamcp.ViaMCP
import viamcp.utils.JLoggerToLog4j
import java.io.File
import java.util.logging.Logger

object Wilt {
    const val MOD_NAME = "WiltLunar"
    const val MOD_VERSION = "v1.0"
    const val MOD_OWNER = "MolokyMC-Team"
    const val MOD_DEVELOPERS = "WaWa"

    val jLogger: Logger = JLoggerToLog4j(LogManager.getLogger("WiltLunar"))

    @JvmStatic
    val dir = File(Minecraft.getMinecraft().mcDataDir, "WiltLunar")

    lateinit var moduleManager: ModuleManager
    lateinit var altManager: AltManager

    fun startGame(){
        val l = System.currentTimeMillis()
        jLogger.info("Loading $MOD_NAME $MOD_VERSION, By $MOD_OWNER, Developer $MOD_DEVELOPERS")
        Display.setTitle(Display.getTitle() + " | $MOD_NAME $MOD_VERSION - By $MOD_OWNER")

        moduleManager = ModuleManager()
        moduleManager.loadModules()
        jLogger.info("Modules Loaded.")

        altManager = AltManager()
        jLogger.info("AltManager Loaded.")

        ViaMCP.getInstance().start()
        ViaMCP.getInstance().initAsyncSlider()
        jLogger.info("ViaLunar Loaded.")
        //ViaMCP.getInstance().version = 340

        jLogger.info("All Loaded. (${System.currentTimeMillis() - l} ms)")
    }
}