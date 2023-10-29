package op.wawa.wilt.module

import op.wawa.wilt.module.modules.Germ
import op.wawa.wilt.module.modules.VexView
import org.cubewhy.lunarcn.loader.api.event.EventBus

class ModuleManager {
    val modules = listOf(
        Germ(),
        VexView()
    )
    fun loadModules(){
        modules.forEach { EventBus.subscribe(it) }
    }
}