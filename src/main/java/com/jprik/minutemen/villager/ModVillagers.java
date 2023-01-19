package com.jprik.minutemen.villager;

import com.google.common.collect.ImmutableSet;
import com.jprik.minutemen.MinutemenMod;
import com.jprik.minutemen.registry.BlockRegistry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES
            = DeferredRegister.create(ForgeRegistries.POI_TYPES, MinutemenMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS
            = DeferredRegister.create(ForgeRegistries.PROFESSIONS, MinutemenMod.MOD_ID);

    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }

    public static final RegistryObject<PoiType> RASSAMBLEMENT_POI = POI_TYPES.register("rassamblement",
            () -> new PoiType("rassamblement", PoiType.getBlockStates(BlockRegistry.RASSAMBLEMENT.get()), 16, 1));

    public static final RegistryObject<VillagerProfession> MINUTEMAN_PROFESSION =
            VILLAGER_PROFESSIONS.register("minuteman", () -> new VillagerProfession("minuteman", RASSAMBLEMENT_POI.get(),
                    ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_WEAPONSMITH));

    public static void registerPOIs() {
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates", PoiType.class).invoke(null, RASSAMBLEMENT_POI.get());
        } catch (InvocationTargetException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }
}
