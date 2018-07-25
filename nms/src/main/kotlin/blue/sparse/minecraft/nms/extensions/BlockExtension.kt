package blue.sparse.minecraft.nms.extensions

import blue.sparse.minecraft.core.data.nbt.Compound
import blue.sparse.minecraft.nms.NMSModule
import org.bukkit.block.Block

//TODO: potentially a `toNBT`

var Block.nbt
	get() = NMSModule.blockNMS.getNBT(this)
	set(value) {
		NMSModule.blockNMS.setNBT(this, value ?: return)
	}

inline fun <R> Block.editNBT(body: Compound.() -> R): R {
	val nbt = nbt ?: throw IllegalAccessException("Block type $type does not carry NBT.")
	val result = nbt.run(body)
	this.nbt = nbt
	return result
}

val Block.hasNBT get() = NMSModule.blockNMS.hasNBT(this)