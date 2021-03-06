package blue.sparse.minecraft.inventory

import blue.sparse.minecraft.SparseMCAPIPlugin
import blue.sparse.minecraft.core.extensions.server
import blue.sparse.minecraft.inventory.item.CustomBlockListener
import blue.sparse.minecraft.inventory.item.CustomItemListener
import blue.sparse.minecraft.inventory.menu.MenuListener
import blue.sparse.minecraft.module.Module
import blue.sparse.minecraft.module.ModuleType
import blue.sparse.minecraft.nms.placeholders.ItemReplacer
import blue.sparse.minecraft.scheduler.extensions.scheduler

object InventoryModule: Module {
	override val type = ModuleType.INVENTORY
	
	override fun onEnable() {
		val pl = SparseMCAPIPlugin.getPlugin()
		val pm = server.pluginManager
//		pm.registerEvents(InventoryListener, pl)
		pm.registerEvents(CustomBlockListener, pl)
		pm.registerEvents(CustomItemListener, pl)
		pm.registerEvents(MenuListener, pl)
		scheduler.scheduleSyncRepeatingTask(pl, MenuListener::tick, 1L, 1L)

		ItemReplacer.register(pl, CustomItemListener)
	}
}