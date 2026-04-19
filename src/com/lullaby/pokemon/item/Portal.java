package com.lullaby.pokemon.item;

/**
 * 传送门
 */
public class Portal extends Item {
    /**
     * 是否是通往下一关的传送门
     */
    private final boolean next;

    public Portal(boolean next) {
        super("传送门");
        this.next = next;
    }

    @Override
    public String getItemInformation() {
        if (discovery) {
            return next ? "→" : "←";
        }
        return "■";
    }

    /**
     * 传送门是否通往下一关
     * @return 是/否
     */
    public boolean isNext () {
        return next;
    }
}
