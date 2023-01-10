package com.laudynetwork.challenges.animetedGUI;/*package com.laudynetwork.challenges.animetedGUI;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class OneLine extends Mod {
    private void changeNegativeNetherBlock() {
        World llIIlIlIlIllIll = Bukkit.getWorld(lIIllIIl[lIIllIll[18]]);

        do {
            int llIIlIlIlIlIllI;
            if (!llllIlllI(llIIlIlIlIlllII.empty_nether.getBlockAt(lIIllIll[0], lIIllIll[0], llIIlIlIlIlllII.coordinates_nether[lIIllIll[2]]).getType().equals(Material.BEDROCK))) {
                if (lllllIlII($assertionsDisabled) && lllllIllI(llIIlIlIlIllIll)) {
                    throw new AssertionError();
                }

                Location llIIlIlIlIllIlI = llIIlIlIlIllIll.getBlockAt(lIIllIll[0], lIIllIll[0], llIIlIlIlIlllII.coordinates_nether[lIIllIll[2]]).getLocation();
                if (lllllIlII(llIIlIlIlIllIlI.getChunk().isLoaded())) {
                    llIIlIlIlIllIlI.getChunk().load();
                    "".length();
                }

                llIIlIlIlIlIllI = lIIllIll[0];

                do {
                    if (!lllllIIlI(llIIlIlIlIlIllI, lIIllIll[16])) {
                        return;
                    }

                    llIIlIlIlIllIlI = llIIlIlIlIllIll.getBlockAt(lIIllIll[0], llIIlIlIlIlIllI, llIIlIlIlIlllII.coordinates_nether[lIIllIll[2]]).getLocation();
                    llIIlIlIlIllIlI.setY((double) llIIlIlIlIlIllI);
                    llIIlIlIlIlllII.changeBlock(llIIlIlIlIlllII.empty_nether, llIIlIlIlIllIlI, llIIlIlIlIlIllI, llIIlIlIlIlllII.coordinates_nether[lIIllIll[2]]);
                    ++llIIlIlIlIlIllI;
                    "".length();
                } while (" ".length() > 0);

                return;
            }

            if (llllllIII(llIIlIlIlIlllII.coordinates_nether[lIIllIll[0]], llIIlIlIlIlllII.coordinates_nether[lIIllIll[2]])) {
                return;
            }

            int[] llIIlIlIlIlllll = llIIlIlIlIlllII.coordinates_nether;
            llIIlIlIlIlIllI = lIIllIll[2];
            int var10001 = lIIllIll[2];
            llIIlIlIlIlllll[var10001] -= lIIllIll[1];
            "".length();
        } while (null == null);

    }

    public boolean onSettingsClick(InventoryClickEvent llIIlIllIlIlllI) {
        if (lllllIlII(super.onSettingsClick(llIIlIllIlIlllI))) {
            return (boolean) lIIllIll[1];
        } else {
            char llIIlIllIlIllIl = (Player) llIIlIllIlIlllI.getWhoClicked();
            llIIlIllIlIlllI.setCancelled((boolean) lIIllIll[1]);
            return (boolean) lIIllIll[1];
        }
    }

    private static String lllIllIll(String llIIlIlIIIlllIl, String llIIlIlIIIlllII) {
        llIIlIlIIIlllIl = new String(Base64.getDecoder().decode(llIIlIlIIIlllIl.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        StringBuilder llIIlIlIIlIIIII = new StringBuilder();
        float llIIlIlIIIllIlI = llIIlIlIIIlllII.toCharArray();
        boolean llIIlIlIIIllIIl = lIIllIll[0];
        Exception llIIlIlIIIllIII = llIIlIlIIIlllIl.toCharArray();
        String llIIlIlIIIlIlll = llIIlIlIIIllIII.length;
        short llIIlIlIIIlIllI = lIIllIll[0];

        do {
            if (!lllllIIlI(llIIlIlIIIlIllI, llIIlIlIIIlIlll)) {
                return String.valueOf(llIIlIlIIlIIIII);
            }

            float llIIlIlIIIlIlIl = llIIlIlIIIllIII[llIIlIlIIIlIllI];
            llIIlIlIIlIIIII.append((char) (llIIlIlIIIlIlIl ^ llIIlIlIIIllIlI[llIIlIlIIIllIIl % llIIlIlIIIllIlI.length]));
            "".length();
            ++llIIlIlIIIllIIl;
            ++llIIlIlIIIlIllI;
            "".length();
        } while (-" ".length() < 0);

        return null;
    }

    private static void llllIIlIl() {
        lIIllIIl = new String[lIIllIll[56]];
        lIIllIIl[lIIllIll[0]] = lllIllIll("HRw3Chw8Fw==", "RrRFu");
        lIIllIIl[lIIllIll[1]] = lllIllIll("FSQAJQI0Lw==", "ZJeik");
        lIIllIIl[lIIllIll[2]] = lllIlllII("quBR/a4/t6Q=", "SWXyu");
        lIIllIIl[lIIllIll[4]] = lllIlllIl("OQAjIdC9ekOC6gBWrufGfaNxhy4tZAhy", "gbuWt");
        lIIllIIl[lIIllIll[8]] = lllIllIll("", "KjySP");
        lIIllIIl[lIIllIll[9]] = lllIlllIl("k4GYDkQmA23O9xhl93+6c2cs1akhcL2N", "Nzqrt");
        lIIllIIl[lIIllIll[10]] = lllIlllIl("YejTph3nJNaI98aMF0AkcTwniNR+s/U9dqP/qJtMDAvHN5r7sQNdqTWQ7vbAZwiE", "koxIZ");
        lIIllIIl[lIIllIll[11]] = lllIllIll("elLDsy/DqTUzIT8pPxw7ISM/HHQJID4=", "ZrTLN");
        lIIllIIl[lIIllIll[7]] = lllIlllIl("Yk3A0JClOtk=", "sQEVV");
        lIIllIIl[lIIllIll[12]] = lllIlllII("OeaTnJjZEH9ts9p30tBDOAsxXm4w3igsQ68UDE//Mqs=", "pFZNH");
        lIIllIIl[lIIllIll[3]] = lllIllIll("", "raqgP");
        lIIllIIl[lIIllIll[13]] = lllIlllIl("Wp0c0SX9zS8=", "MmdMD");
        lIIllIIl[lIIllIll[14]] = lllIlllII("hs+pmgUuCaewHT+VtAY9+Q==", "Rrcqt");
        lIIllIIl[lIIllIll[15]] = lllIllIll("AQkbHAM=", "vfipg");
        lIIllIIl[lIIllIll[17]] = lllIllIll("GSoWJT0=", "nEdIY");
        lIIllIIl[lIIllIll[6]] = lllIlllII("WWAczoG1iD5RKgxMYSGLQA==", "OTFaL");
        lIIllIIl[lIIllIll[18]] = lllIlllII("+xRuVJRUFapxQmFOGM1LRA==", "qyAEZ");
        lIIllIIl[lIIllIll[21]] = lllIlllIl("DknUe/ncqy0BlT6cmTnNc3xM0wHo/W74", "eFxbv");
        lIIllIIl[lIIllIll[22]] = lllIllIll("KzA4BDMkKjhJJCIqJAMuIyQiAjRjdA==", "MEVgG");
        lIIllIIl[lIIllIll[23]] = lllIllIll("Ay89BjkMNT1LLgo1IQEkCzsnAD5LaA==", "eZSeM");
        lIIllIIl[lIIllIll[24]] = lllIlllII("SkD7ONlL0P1hLURLY1DjTbW4ifRYDY0U", "lOgjU");
        lIIllIIl[lIIllIll[25]] = lllIllIll("Ijk0ASctIzRMMCsjKAY6Ki0uByAbIj8WOyE+dFI=", "DLZbS");
        lIIllIIl[lIIllIll[26]] = lllIlllII("SeUKhA+TLNkh1r68xFd2cWeORMzVWOHogKuscWxhNwo=", "eItdB");
        lIIllIIl[lIIllIll[27]] = lllIlllIl("wqA15ZwvzIrSyzVUu0SMSAtDdIut2Fuv40PxkC0Q9gM=", "HMYLU");
        lIIllIIl[lIIllIll[28]] = lllIlllII("NUOE0n7ZNPRqL3j8UBpLyvF6XaHnFkUayScqRzxFLMw=", "wBPkD");
        lIIllIIl[lIIllIll[29]] = lllIllIll("CAcUKQYHHRRkEQEdCC4bABMOLwFAQg==", "nrzJr");
        lIIllIIl[lIIllIll[30]] = lllIlllIl("Ho8intGtNl8tIZ4W/4tFvTMrGk5PTcqm", "uTpiC");
        lIIllIIl[lIIllIll[31]] = lllIlllII("vMGxU+w+rrZoWPu/MdWiLDG4ApV03ibr", "BlNkd");
        lIIllIIl[lIIllIll[32]] = lllIllIll("CyULOgwEPwt3GwI/Fz0RAzERPAtDYw==", "mPeYx");
        lIIllIIl[lIIllIll[33]] = lllIlllII("NE4EBZtPjF32ZI+xhwBOpDOVzPB6tQJW8z4GZTt4oMk=", "PeNFJ");
        lIIllIIl[lIIllIll[34]] = lllIlllIl("weTl99R5yt+7tAZQuQAx94xiLY5NxZ+tEhyRjgxKpro=", "jfgwL");
        lIIllIIl[lIIllIll[35]] = lllIlllIl("qXnCOL0i00VoCjelxCvDj2iaQniQvy1fp2Qdg1hWfDk=", "jSUVz");
        lIIllIIl[lIIllIll[36]] = lllIlllIl("TqcT/frWMGDBs5opBO4gxUlYQRyP9uO+L2AkD7x6Lg0=", "cdZjF");
        lIIllIIl[lIIllIll[37]] = lllIlllIl("f4i6GawgjilGBMIAnwaAKZIzedMAoKco", "aeEbN");
        lIIllIIl[lIIllIll[38]] = lllIllIll("IAMiLTAvGSJgJykZPiotKBc4KzdoRw==", "FvLND");
        lIIllIIl[lIIllIll[39]] = lllIlllII("xxU+B5uLWSh3kiGqDFoOgvUmhYDv6Fji", "sobMC");
        lIIllIIl[lIIllIll[40]] = lllIlllIl("By1MdmeWBYQwxCfpEZu5K1Km1UVaQ50Z", "nPvlu");
        lIIllIIl[lIIllIll[41]] = lllIllIll("Bz4HIDkIJAdtLg4kGyckDyodJj4+JQw3JQQ5R3M=", "aKiCM");
        lIIllIIl[lIIllIll[42]] = lllIllIll("MQAGLBA+GgZhBzgaGisNORQcKhcIGw07DDIHRn4=", "WuhOd");
        lIIllIIl[lIIllIll[43]] = lllIlllII("z32cNaDkSCtQd5F8EjUhaH6a86328zepQu4FGlBR1fg=", "IGjov");
        lIIllIIl[lIIllIll[44]] = lllIlllIl("+Rq4sPjQztdYgqHbIis8FT7qnU6NEXBo+4AKHWh7CFU=", "ZpqwK");
        lIIllIIl[lIIllIll[45]] = lllIllIll("NCQYMS07Phh8Oj0+BDYwPDACNyp8YQ==", "RQvRY");
        lIIllIIl[lIIllIll[46]] = lllIlllIl("XlQSIyAFHSnZn5SjscGhGKY1tGTDT5uO", "qXFit");
        lIIllIIl[lIIllIll[47]] = lllIllIll("Cw8qKBYEFSplAQIVNi8LAxswLhFDSA==", "mzDKb");
        lIIllIIl[lIIllIll[48]] = lllIlllIl("hFujBvxgP45Qjgn1aacZSCeFC6I1mpVT", "TYhFG");
        lIIllIIl[lIIllIll[49]] = lllIlllIl("aD4hQZZ6HtkW/RxGmKrmZUbI8u6/4Q2HkqndzbIEUbY=", "HkEYN");
        lIIllIIl[lIIllIll[50]] = lllIlllII("UkwsUE8KDBojtpmXnuRRNqKSHU7Ul8jqhDAoUyLVxEU=", "JJaVM");
        lIIllIIl[lIIllIll[51]] = lllIllIll("IR4eARcuBB5MACgEAgYKKQoEBxAYBRUWCyIZXlA=", "Gkpbc");
        lIIllIIl[lIIllIll[52]] = lllIlllII("d7aVaYvlOLzbC8sWGY7Ubb9JW1aJWUsZ3tJ/xeFqnwg=", "ISOCz");
        lIIllIIl[lIIllIll[53]] = lllIllIll("Hww6EBM=", "zaJdj");
        lIIllIIl[lIIllIll[54]] = lllIlllII("YIFKQBiHCt6/auLf9q9W2A==", "zPeKw");
        lIIllIIl[lIIllIll[55]] = lllIlllIl("5eQ4fb388yY=", "hQdbw");
    }

    private static boolean lllllIllI(Object var0) {
        return var0 == null;
    }

    public void setStatus(ModManager.ModStatus llIIlIlIIlllllI) {
        super.setStatus(llIIlIlIIlllllI);
        Iterator llIIlIlIIlllIll;
        Player llIIlIlIlIIIIIl;
        switch (SyntheticClass_1.$SwitchMap$tv$banko$butils$mods$ModManager$ModStatus[llIIlIlIIlllllI.ordinal()]) {
            case 1:
                if (lllllIllI(llIIlIlIIllllIl.empty)) {
                    llIIlIlIIllllIl.generateWorld();
                }

                llIIlIlIIlllIll = Bukkit.getOnlinePlayers().iterator();

                while (llllIlllI(llIIlIlIIlllIll.hasNext())) {
                    llIIlIlIlIIIIIl = (Player) llIIlIlIIlllIll.next();
                    llIIlIlIlIIIIIl.teleport(llIIlIlIIllllIl.empty.getHighestBlockAt(lIIllIll[0], lIIllIll[0]).getLocation().clone().add(0.0, 1.0, 0.0));
                    "".length();
                    "".length();
                    if (" ".length() < 0) {
                        return;
                    }
                }

                "".length();
                if (-" ".length() > "   ".length()) {
                    return;
                }
                break;
            case 2:
                llIIlIlIIlllIll = Bukkit.getOnlinePlayers().iterator();

                while (llllIlllI(llIIlIlIIlllIll.hasNext())) {
                    llIIlIlIlIIIIIl = (Player) llIIlIlIIlllIll.next();
                    llIIlIlIlIIIIIl.teleport(((World) Objects.requireNonNull(Bukkit.getWorld(lIIllIIl[lIIllIll[55]]))).getSpawnLocation());
                    "".length();
                    "".length();
                    if ("   ".length() != "   ".length()) {
                        return;
                    }
                }
        }

    }

    private static boolean llllllIII(int var0, int var1) {
        return var0 >= var1;
    }

    @EventHandler
    public void onWorldChange(PlayerTeleportEvent llIIlIllIlllIIl) {
        if (llllIlllI(llIIlIllIlllIII.isEnabled())) {
            if (lllllIllI(llIIlIllIlllIIl.getTo())) {
                return;
            }

            Location llIIlIllIlllIll = llIIlIllIlllIIl.getTo();
            if (llllIlllI(((World) Objects.requireNonNull(llIIlIllIlllIIl.getTo().getWorld())).getEnvironment().equals(Environment.NORMAL)) && llllIlllI(((World) Objects.requireNonNull(llIIlIllIlllIIl.getFrom().getWorld())).getEnvironment().equals(Environment.NETHER))) {
                llIIlIllIlllIll.setWorld(llIIlIllIlllIII.empty);
                llIIlIllIlllIIl.setTo(llIIlIllIlllIll);
                "".length();
                if ("  ".length() == 0) {
                    return;
                }
            } else if (llllIlllI(llIIlIllIlllIIl.getTo().getWorld().getEnvironment().equals(Environment.NETHER)) && llllIlllI(((World) Objects.requireNonNull(llIIlIllIlllIIl.getFrom().getWorld())).getEnvironment().equals(Environment.NORMAL))) {
                llIIlIllIlllIll.setWorld(llIIlIllIlllIII.empty_nether);
                llIIlIllIlllIIl.setTo(llIIlIllIlllIll);
            }
        }

    }

    public void schedulerTask() {
        if (llllIlllI(llIIlIllllIllII.isEnabled())) {
            if (lllllIIII(llllIllll(llIIlIllllIllII.millis, System.currentTimeMillis()))) {
                llIIlIllllIllII.millis = System.currentTimeMillis() + 2000L;
                String var10000 = Arrays.toString(llIIlIllllIllII.coordinates);
                Debug.sendInfo("coordinates: " + var10000 + " | coordinates_nether: " + Arrays.toString(llIIlIllllIllII.coordinates_nether));
            }

            if (lllllIIIl(llIIlIllllIllII.simulateFortress) && lllllIIII(llllIllll(llIIlIllllIllII.nextWave, System.currentTimeMillis()))) {
                Random llIIlIllllIlllI = new Random();
                Exception llIIlIllllIlIlI = lIIllIll[0];

                while (lllllIIlI(llIIlIllllIlIlI, llIIlIllllIlllI.nextInt(lIIllIll[4]) + lIIllIll[2])) {
                    int var6;
                    if (llllIlllI(llIIlIllllIlllI.nextBoolean())) {
                        var6 = lIIllIll[5];
                        "".length();
                        if ("   ".length() <= " ".length()) {
                            return;
                        }
                    } else {
                        var6 = lIIllIll[1];
                    }

                    boolean llIIlIllllIlIIl = var6 * llIIlIllllIlllI.nextInt(lIIllIll[6]);
                    if (llllIlllI(llIIlIllllIlllI.nextBoolean())) {
                        var6 = lIIllIll[5];
                        "".length();
                        if ("   ".length() < 0) {
                            return;
                        }
                    } else {
                        var6 = lIIllIll[1];
                    }

                    char llIIlIllllIlIII = var6 * llIIlIllllIlllI.nextInt(lIIllIll[6]);
                    Exception llIIlIllllIIlll = llIIlIllllIllII.simulateFortress.clone().add((double) llIIlIllllIlIIl, 0.0, (double) llIIlIllllIlIII);
                    if (llllIlllI(llIIlIllllIIlll.clone().add(0.0, -1.0, 0.0).getBlock().getType().equals(Material.NETHER_BRICKS)) && llllIlllI(llIIlIllllIIlll.getBlock().getType().equals(Material.AIR)) && llllIlllI(llIIlIllllIIlll.clone().add(0.0, 1.0, 0.0).getBlock().getType().equals(Material.AIR)) && llllIlllI(llIIlIllllIIlll.clone().add(0.0, 2.0, 0.0).getBlock().getType().equals(Material.AIR))) {
                        ((World) Objects.requireNonNull(llIIlIllllIIlll.getWorld())).spawnEntity(llIIlIllllIIlll, EntityType.WITHER_SKELETON);
                        "".length();
                    }

                    ++llIIlIllllIlIlI;
                    "".length();
                    if ("   ".length() != "   ".length()) {
                        return;
                    }
                }

                llIIlIllllIllII.nextWave = System.currentTimeMillis() + 20000L;
            }

            if (lllllIIlI(llIIlIllllIllII.coordinates[lIIllIll[0]], llIIlIllllIllII.coordinates[lIIllIll[2]])) {
                llIIlIllllIllII.changeNegativeOverworldBlock();
                "".length();
                if (-"  ".length() >= 0) {
                    return;
                }
            } else if (lllllIIll(llIIlIllllIllII.coordinates[lIIllIll[1]], llIIlIllllIllII.coordinates[lIIllIll[4]])) {
                llIIlIllllIllII.changePositiveOverworldBlock();
            }

            if (lllllIIlI(llIIlIllllIllII.coordinates_nether[lIIllIll[0]], llIIlIllllIllII.coordinates_nether[lIIllIll[2]])) {
                llIIlIllllIllII.changeNegativeNetherBlock();
                "".length();
                if ((153 ^ 157) < (28 ^ 24)) {
                    return;
                }
            } else if (lllllIIll(llIIlIllllIllII.coordinates_nether[lIIllIll[1]], llIIlIllllIllII.coordinates_nether[lIIllIll[4]])) {
                llIIlIllllIllII.changePositiveNetherBlock();
            }
        }

    }

    private static boolean lllllIIII(int var0) {
        return var0 <= 0;
    }

    private static boolean lllllIlII(int var0) {
        return var0 == 0;
    }

    @EventHandler
    public void onBlockFromTo(BlockFromToEvent llIIlIlllIIlllI) {
        if (llllIlllI(llIIlIlllIIllll.isEnabled()) && (!lllllIlII(llIIlIlllIIlllI.getBlock().getWorld().equals(llIIlIlllIIllll.empty)) || llllIlllI(llIIlIlllIIlllI.getBlock().getWorld().equals(llIIlIlllIIllll.empty_nether))) && lllllIlII(llIIlIlllIIlllI.getBlock().getChunk().getX()) && lllllIlII(llIIlIlllIIlllI.getBlock().getX()) && llllIlllI(llIIlIlllIIlllI.getToBlock().getX())) {
            llIIlIlllIIlllI.setCancelled((boolean) lIIllIll[1]);
        }

    }

    private void changePositiveNetherBlock() {
        World llIIlIlIllIlIIl = Bukkit.getWorld(lIIllIIl[lIIllIll[6]]);

        do {
            int llIIlIlIllIIlII;
            if (!llllIlllI(llIIlIlIllIIlll.empty_nether.getBlockAt(lIIllIll[0], lIIllIll[0], llIIlIlIllIIlll.coordinates_nether[lIIllIll[4]]).getType().equals(Material.BEDROCK))) {
                if (lllllIlII($assertionsDisabled) && lllllIllI(llIIlIlIllIlIIl)) {
                    throw new AssertionError();
                }

                String llIIlIlIllIIlIl = llIIlIlIllIlIIl.getBlockAt(lIIllIll[0], lIIllIll[0], llIIlIlIllIIlll.coordinates_nether[lIIllIll[4]]).getLocation();
                if (lllllIlII(llIIlIlIllIIlIl.getChunk().isLoaded())) {
                    llIIlIlIllIIlIl.getChunk().load();
                    "".length();
                }

                llIIlIlIllIIlII = lIIllIll[0];

                do {
                    if (!lllllIIlI(llIIlIlIllIIlII, lIIllIll[16])) {
                        return;
                    }

                    llIIlIlIllIIlIl = llIIlIlIllIlIIl.getBlockAt(lIIllIll[0], llIIlIlIllIIlII, llIIlIlIllIIlll.coordinates_nether[lIIllIll[4]]).getLocation();
                    llIIlIlIllIIlIl.setY((double) llIIlIlIllIIlII);
                    llIIlIlIllIIlll.changeBlock(llIIlIlIllIIlll.empty_nether, llIIlIlIllIIlIl, llIIlIlIllIIlII, llIIlIlIllIIlll.coordinates_nether[lIIllIll[4]]);
                    ++llIIlIlIllIIlII;
                    "".length();
                } while (((51 ^ 125) & ~(246 ^ 184)) == ((102 ^ 111) & ~(167 ^ 174)));

                return;
            }

            if (lllllIlll(llIIlIlIllIIlll.coordinates_nether[lIIllIll[1]], llIIlIlIllIIlll.coordinates_nether[lIIllIll[4]])) {
                return;
            }

            String llIIlIlIllIIlIl = llIIlIlIllIIlll.coordinates_nether;
            llIIlIlIllIIlII = lIIllIll[4];
            int var10001 = lIIllIll[4];
            llIIlIlIllIIlIl[var10001] += lIIllIll[1];
            "".length();
        } while ((193 ^ 197) != 0);

    }

    public void createMod() {
        int[] var10001 = new int[lIIllIll[8]];
        var10001[lIIllIll[0]] = lIIllIll[19];
        var10001[lIIllIll[1]] = lIIllIll[20];
        var10001[lIIllIll[2]] = lIIllIll[0];
        var10001[lIIllIll[4]] = lIIllIll[0];
        llIIlIlIlIlIIII.coordinates = var10001;
        var10001 = new int[lIIllIll[8]];
        var10001[lIIllIll[0]] = lIIllIll[19];
        var10001[lIIllIll[1]] = lIIllIll[20];
        var10001[lIIllIll[2]] = lIIllIll[0];
        var10001[lIIllIll[4]] = lIIllIll[0];
        llIIlIlIlIlIIII.coordinates_nether = var10001;
        llIIlIlIlIlIIII.coordinates[lIIllIll[0]] = (Integer) llIIlIlIlIlIIII.createValue(lIIllIIl[lIIllIll[29]], lIIllIll[19]);
        llIIlIlIlIlIIII.coordinates[lIIllIll[1]] = (Integer) llIIlIlIlIlIIII.createValue(lIIllIIl[lIIllIll[30]], lIIllIll[20]);
        llIIlIlIlIlIIII.coordinates[lIIllIll[2]] = (Integer) llIIlIlIlIlIIII.createValue(lIIllIIl[lIIllIll[31]], lIIllIll[0]);
        llIIlIlIlIlIIII.coordinates[lIIllIll[4]] = (Integer) llIIlIlIlIlIIII.createValue(lIIllIIl[lIIllIll[32]], lIIllIll[0]);
        llIIlIlIlIlIIII.coordinates_nether[lIIllIll[0]] = (Integer) llIIlIlIlIlIIII.createValue(lIIllIIl[lIIllIll[33]], lIIllIll[19]);
        llIIlIlIlIlIIII.coordinates_nether[lIIllIll[1]] = (Integer) llIIlIlIlIlIIII.createValue(lIIllIIl[lIIllIll[34]], lIIllIll[20]);
        llIIlIlIlIlIIII.coordinates_nether[lIIllIll[2]] = (Integer) llIIlIlIlIlIIII.createValue(lIIllIIl[lIIllIll[35]], lIIllIll[0]);
        llIIlIlIlIlIIII.coordinates_nether[lIIllIll[4]] = (Integer) llIIlIlIlIlIIII.createValue(lIIllIIl[lIIllIll[36]], lIIllIll[0]);
    }

    static {
        llllIllIl();
        llllIIlIl();
        int var10000;
        if (lllllIlII(OneLine.class.desiredAssertionStatus())) {
            var10000 = lIIllIll[1];
            "".length();
            if (((110 ^ 14) & ~(241 ^ 145)) > "  ".length()) {
                return;
            }
        } else {
            var10000 = lIIllIll[0];
        }

        $assertionsDisabled = (boolean) var10000;
    }

    private static String lllIlllII(String llIIlIlIIIIlIll, String llIIlIlIIIIllII) {
        try {
            int llIIlIlIIIIlIIl = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(llIIlIlIIIIllII.getBytes(StandardCharsets.UTF_8)), "Blowfish");
            Cipher llIIlIlIIIIllll = Cipher.getInstance("Blowfish");
            llIIlIlIIIIllll.init(lIIllIll[2], llIIlIlIIIIlIIl);
            return new String(llIIlIlIIIIllll.doFinal(Base64.getDecoder().decode(llIIlIlIIIIlIll.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent llIIlIlllIlIlIl) {
        if (llllIlllI(llIIlIlllIlIllI.isEnabled())) {
            Player llIIlIlllIllIIl = llIIlIlllIlIlIl.getPlayer();
            if (llllIlllI(llIIlIlllIlIlIl.getMessage().toLowerCase().startsWith(lIIllIIl[lIIllIll[4]]))) {
                llIIlIlllIlIlIl.setCancelled((boolean) lIIllIll[1]);
                if (llllIlllI(llIIlIlllIllIIl.getWorld().getEnvironment().equals(Environment.NETHER))) {
                    llIIlIlllIlIllI.simulateFortress = llIIlIlllIllIIl.getLocation();
                    llIIlIlllIllIIl.sendMessage(llIIlIlllIlIllI.getPrefix() + "§7Im Umkreis von §930 Blöcken §7können nun §eWitherskelette §7spawnen.");
                    "".length();
                    if (-" ".length() >= 0) {
                        return;
                    }
                } else {
                    llIIlIlllIllIIl.sendMessage(llIIlIlllIlIllI.getPrefix() + "§cEs kann auf dieser Welt keine Festung simuliert werden.");
                }
            }
        }

    }

    public void loadMod() {
        int[] var10001 = new int[lIIllIll[8]];
        var10001[lIIllIll[0]] = lIIllIll[19];
        var10001[lIIllIll[1]] = lIIllIll[20];
        var10001[lIIllIll[2]] = lIIllIll[0];
        var10001[lIIllIll[4]] = lIIllIll[0];
        llIIlIlIlIlIIll.coordinates = var10001;
        var10001 = new int[lIIllIll[8]];
        var10001[lIIllIll[0]] = lIIllIll[19];
        var10001[lIIllIll[1]] = lIIllIll[20];
        var10001[lIIllIll[2]] = lIIllIll[0];
        var10001[lIIllIll[4]] = lIIllIll[0];
        llIIlIlIlIlIIll.coordinates_nether = var10001;
        llIIlIlIlIlIIll.coordinates[lIIllIll[0]] = (Integer) llIIlIlIlIlIIll.loadValue(lIIllIIl[lIIllIll[21]], lIIllIll[19]);
        llIIlIlIlIlIIll.coordinates[lIIllIll[1]] = (Integer) llIIlIlIlIlIIll.loadValue(lIIllIIl[lIIllIll[22]], lIIllIll[20]);
        llIIlIlIlIlIIll.coordinates[lIIllIll[2]] = (Integer) llIIlIlIlIlIIll.loadValue(lIIllIIl[lIIllIll[23]], lIIllIll[0]);
        llIIlIlIlIlIIll.coordinates[lIIllIll[4]] = (Integer) llIIlIlIlIlIIll.loadValue(lIIllIIl[lIIllIll[24]], lIIllIll[0]);
        llIIlIlIlIlIIll.coordinates_nether[lIIllIll[0]] = (Integer) llIIlIlIlIlIIll.loadValue(lIIllIIl[lIIllIll[25]], lIIllIll[19]);
        llIIlIlIlIlIIll.coordinates_nether[lIIllIll[1]] = (Integer) llIIlIlIlIlIIll.loadValue(lIIllIIl[lIIllIll[26]], lIIllIll[20]);
        llIIlIlIlIlIIll.coordinates_nether[lIIllIll[2]] = (Integer) llIIlIlIlIlIIll.loadValue(lIIllIIl[lIIllIll[27]], lIIllIll[0]);
        llIIlIlIlIlIIll.coordinates_nether[lIIllIll[4]] = (Integer) llIIlIlIlIlIIll.loadValue(lIIllIIl[lIIllIll[28]], lIIllIll[0]);
    }

    private void changeBlock(World llIIlIllIlIIIlI, Location llIIlIllIlIIIIl, int llIIlIllIlIIIII, int llIIlIllIIlllll) {
        byte llIIlIllIIllIII = llIIlIllIlIIIlI.getBlockAt(lIIllIll[0], llIIlIllIlIIIII, llIIlIllIIlllll);
        Block llIIlIllIIlllIl = ((World) Objects.requireNonNull(llIIlIllIlIIIIl.getWorld())).getBlockAt(llIIlIllIlIIIIl.getBlockX(), llIIlIllIlIIIIl.getBlockY(), llIIlIllIlIIIIl.getBlockZ());
        llIIlIllIIllIII.setType(llIIlIllIIlllIl.getType());
        llIIlIllIIllIII.getState().setData(llIIlIllIIlllIl.getState().getData());
        llIIlIllIIllIII.getState().setBlockData(llIIlIllIIlllIl.getState().getBlockData());
        llIIlIllIIllIII.setBiome(llIIlIllIIlllIl.getBiome());
    }

    private static boolean lllllIIIl(Object var0) {
        return var0 != null;
    }

    private void changeNegativeOverworldBlock() {
        World llIIlIlIlllIlll = Bukkit.getWorld(lIIllIIl[lIIllIll[17]]);

        do {
            int llIIlIlIllllIIl;
            if (!llllIlllI(llIIlIlIllllIII.empty.getBlockAt(lIIllIll[0], lIIllIll[0], llIIlIlIllllIII.coordinates[lIIllIll[2]]).getType().equals(Material.BEDROCK))) {
                if (lllllIlII($assertionsDisabled) && lllllIllI(llIIlIlIlllIlll)) {
                    throw new AssertionError();
                }

                Location llIIlIlIlllIllI = llIIlIlIlllIlll.getBlockAt(lIIllIll[0], lIIllIll[0], llIIlIlIllllIII.coordinates[lIIllIll[2]]).getLocation();
                if (lllllIlII(llIIlIlIlllIllI.getChunk().isLoaded())) {
                    llIIlIlIlllIllI.getChunk().load();
                    "".length();
                }

                llIIlIlIllllIIl = lIIllIll[0];

                do {
                    if (!lllllIIlI(llIIlIlIllllIIl, lIIllIll[16])) {
                        return;
                    }

                    llIIlIlIlllIllI = llIIlIlIlllIlll.getBlockAt(lIIllIll[0], llIIlIlIllllIIl, llIIlIlIllllIII.coordinates[lIIllIll[2]]).getLocation();
                    llIIlIlIlllIllI.setY((double) llIIlIlIllllIIl);
                    llIIlIlIllllIII.changeBlock(llIIlIlIllllIII.empty, llIIlIlIlllIllI, llIIlIlIllllIIl, llIIlIlIllllIII.coordinates[lIIllIll[2]]);
                    ++llIIlIlIllllIIl;
                    "".length();
                } while (null == null);

                return;
            }

            if (llllllIII(llIIlIlIllllIII.coordinates[lIIllIll[0]], llIIlIlIllllIII.coordinates[lIIllIll[2]])) {
                return;
            }

            int[] llIIlIlIllllIll = llIIlIlIllllIII.coordinates;
            llIIlIlIllllIIl = lIIllIll[2];
            int var10001 = lIIllIll[2];
            llIIlIlIllllIll[var10001] -= lIIllIll[1];
            "".length();
        } while ((193 ^ 197) == (68 ^ 64));

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent llIIlIlllIIIllI) {
        if (llllIlllI(llIIlIlllIIlIIl.isEnabled())) {
            Player llIIlIlllIIlIlI = llIIlIlllIIIllI.getPlayer();
            if (lllllIlII(llIIlIlllIIlIlI.getWorld().getUID().equals(llIIlIlllIIlIIl.empty.getUID())) && lllllIlII(llIIlIlllIIlIlI.getWorld().getUID().equals(llIIlIlllIIlIIl.empty_nether.getUID()))) {
                llIIlIlllIIlIlI.teleport(llIIlIlllIIlIIl.empty.getHighestBlockAt(lIIllIll[0], lIIllIll[0]).getLocation().clone().add(0.0, 1.0, 0.0));
                "".length();
            }
        }

    }

    private static boolean lllllIIlI(int var0, int var1) {
        return var0 < var1;
    }

    private static int llllIllll(long var0, long var2) {
        long var4;
        return (var4 = var0 - var2) == 0L ? 0 : (var4 < 0L ? -1 : 1);
    }

    public void saveMod(YamlConfiguration llIIlIlIlIIlIIl) {
        llIIlIlIlIIlIIl.set(lIIllIIl[lIIllIll[45]], llIIlIlIlIIlIII.coordinates[lIIllIll[0]]);
        llIIlIlIlIIlIIl.set(lIIllIIl[lIIllIll[46]], llIIlIlIlIIlIII.coordinates[lIIllIll[1]]);
        llIIlIlIlIIlIIl.set(lIIllIIl[lIIllIll[47]], llIIlIlIlIIlIII.coordinates[lIIllIll[2]]);
        llIIlIlIlIIlIIl.set(lIIllIIl[lIIllIll[48]], llIIlIlIlIIlIII.coordinates[lIIllIll[4]]);
        llIIlIlIlIIlIIl.set(lIIllIIl[lIIllIll[49]], llIIlIlIlIIlIII.coordinates_nether[lIIllIll[0]]);
        llIIlIlIlIIlIIl.set(lIIllIIl[lIIllIll[50]], llIIlIlIlIIlIII.coordinates_nether[lIIllIll[1]]);
        llIIlIlIlIIlIIl.set(lIIllIIl[lIIllIll[51]], llIIlIlIlIIlIII.coordinates_nether[lIIllIll[2]]);
        llIIlIlIlIIlIIl.set(lIIllIIl[lIIllIll[52]], llIIlIlIlIIlIII.coordinates_nether[lIIllIll[4]]);
    }

    public OneLine() {
        super(lIIllIIl[lIIllIll[0]], lIIllIIl[lIIllIll[1]], lIIllIIl[lIIllIll[2]], ModType.GAME_CHANGE, PluginType.PAID_NORMAL);
        llIIlIlllllllIl.setSchedulerTicks(lIIllIll[3]);
        llIIlIlllllllIl.millis = System.currentTimeMillis();
        llIIlIlllllllIl.nextWave = System.currentTimeMillis();
    }

    private static boolean llllIlllI(int var0) {
        return var0 != 0;
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent llIIlIllllIIIII) {
        if (llllIlllI(llIIlIlllIlllll.isEnabled()) && (!lllllIlII(llIIlIllllIIIII.getWorld().equals(llIIlIlllIlllll.empty)) || llllIlllI(llIIlIllllIIIII.getWorld().equals(llIIlIlllIlllll.empty_nether))) && lllllIlII(llIIlIllllIIIII.getChunk().getX())) {
            Block llIIlIllllIIIll;
            if (lllllIlIl(llIIlIllllIIIII.getChunk().getZ())) {
                llIIlIllllIIIll = llIIlIllllIIIII.getChunk().getBlock(lIIllIll[0], lIIllIll[0], lIIllIll[7]);
                if (lllllIIlI(llIIlIllllIIIll.getZ(), llIIlIlllIlllll.coordinates[lIIllIll[0]])) {
                    llIIlIlllIlllll.coordinates[lIIllIll[0]] = llIIlIllllIIIll.getZ();
                    llIIlIlllIlllll.coordinates_nether[lIIllIll[0]] = llIIlIllllIIIll.getZ();
                }

                "".length();
                if (-" ".length() > "  ".length()) {
                    return;
                }
            } else {
                llIIlIllllIIIll = llIIlIllllIIIII.getChunk().getBlock(lIIllIll[0], lIIllIll[0], lIIllIll[7]);
                if (lllllIIll(llIIlIllllIIIll.getZ(), llIIlIlllIlllll.coordinates[lIIllIll[1]])) {
                    llIIlIlllIlllll.coordinates[lIIllIll[1]] = llIIlIllllIIIll.getZ();
                    llIIlIlllIlllll.coordinates_nether[lIIllIll[1]] = llIIlIllllIIIll.getZ();
                }
            }
        }

    }

    private void changePositiveOverworldBlock() {
        char llIIlIllIIIIIlI = Bukkit.getWorld(lIIllIIl[lIIllIll[15]]);

        do {
            int llIIlIllIIIIlll;
            if (!llllIlllI(llIIlIllIIIIllI.empty.getBlockAt(lIIllIll[0], lIIllIll[0], llIIlIllIIIIllI.coordinates[lIIllIll[4]]).getType().equals(Material.BEDROCK))) {
                if (lllllIlII($assertionsDisabled) && lllllIllI(llIIlIllIIIIIlI)) {
                    throw new AssertionError();
                }

                char llIIlIllIIIIIIl = llIIlIllIIIIIlI.getBlockAt(lIIllIll[0], lIIllIll[0], llIIlIllIIIIllI.coordinates[lIIllIll[4]]).getLocation();
                if (lllllIlII(llIIlIllIIIIIIl.getChunk().isLoaded())) {
                    llIIlIllIIIIIIl.getChunk().load();
                    "".length();
                }

                llIIlIllIIIIlll = lIIllIll[0];

                do {
                    if (!lllllIIlI(llIIlIllIIIIlll, lIIllIll[16])) {
                        return;
                    }

                    llIIlIllIIIIIIl = llIIlIllIIIIIlI.getBlockAt(lIIllIll[0], llIIlIllIIIIlll, llIIlIllIIIIllI.coordinates[lIIllIll[4]]).getLocation();
                    llIIlIllIIIIIIl.setY((double) llIIlIllIIIIlll);
                    llIIlIllIIIIllI.changeBlock(llIIlIllIIIIllI.empty, llIIlIllIIIIIIl, llIIlIllIIIIlll, llIIlIllIIIIllI.coordinates[lIIllIll[4]]);
                    ++llIIlIllIIIIlll;
                    "".length();
                } while (" ".length() >= 0);

                return;
            }

            if (lllllIlll(llIIlIllIIIIllI.coordinates[lIIllIll[1]], llIIlIllIIIIllI.coordinates[lIIllIll[4]])) {
                return;
            }

            int[] llIIlIllIIIlIIl = llIIlIllIIIIllI.coordinates;
            llIIlIllIIIIlll = lIIllIll[4];
            int var10001 = lIIllIll[4];
            llIIlIllIIIlIIl[var10001] += lIIllIll[1];
            "".length();
        } while ((191 ^ 139 ^ 124 ^ 76) > " ".length());

    }

    private static String lllIlllIl(String llIIlIlIIllIIII, String llIIlIlIIllIIIl) {
        try {
            String llIIlIlIIlIlllI = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(llIIlIlIIllIIIl.getBytes(StandardCharsets.UTF_8)), lIIllIll[7]), "DES");
            Cipher llIIlIlIIllIlII = Cipher.getInstance("DES");
            llIIlIlIIllIlII.init(lIIllIll[2], llIIlIlIIlIlllI);
            return new String(llIIlIlIIllIlII.doFinal(Base64.getDecoder().decode(llIIlIlIIllIIII.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    @EventHandler
    public void onSpawn(EntitySpawnEvent llIIlIllIllllll) {
        if (llllIlllI(llIIlIlllIIIIII.isEnabled()) && llllIlllI(llIIlIllIllllll.getEntity() instanceof Phantom) && llllIlllI(((World) Objects.requireNonNull(llIIlIllIllllll.getLocation().getWorld())).getEnvironment().equals(Environment.NETHER))) {
            llIIlIllIllllll.setCancelled((boolean) lIIllIll[1]);
        }

    }

    private static void llllIllIl() {
        lIIllIll = new int[57];
        lIIllIll[0] = (111 ^ 100) & ~(152 ^ 147);
        lIIllIll[1] = " ".length();
        lIIllIll[2] = "  ".length();
        lIIllIll[3] = 103 + 67 - 47 + 42 ^ 114 + 11 - -23 + 27;
        lIIllIll[4] = "   ".length();
        lIIllIll[5] = -" ".length();
        lIIllIll[6] = 201 ^ 198;
        lIIllIll[7] = 108 ^ 100;
        lIIllIll[8] = 100 ^ 96;
        lIIllIll[9] = 4 ^ 1;
        lIIllIll[10] = 112 ^ 118;
        lIIllIll[11] = 155 ^ 156;
        lIIllIll[12] = 49 + 53 - 31 + 82 ^ 119 + 88 - 188 + 125;
        lIIllIll[13] = 52 ^ 91 ^ 60 ^ 88;
        lIIllIll[14] = 187 ^ 183;
        lIIllIll[15] = 50 + 27 - -99 + 5 ^ 112 + 131 - 242 + 183;
        lIIllIll[16] = 168 + 133 - 282 + 164 + (40 ^ 90) - (165 + 156 - 213 + 93) + (77 ^ 37);
        lIIllIll[17] = 43 ^ 37;
        lIIllIll[18] = "   ".length() ^ 65 ^ 82;
        lIIllIll[19] = -(-26659 & 31658);
        lIIllIll[20] = 29 ^ 121;
        lIIllIll[21] = 158 ^ 143;
        lIIllIll[22] = 167 ^ 191 ^ 121 ^ 115;
        lIIllIll[23] = 147 + 7 - 65 + 96 ^ 28 + 76 - -13 + 53;
        lIIllIll[24] = 215 ^ 148 ^ 91 ^ 12;
        lIIllIll[25] = 5 + 113 - -13 + 46 ^ 160 + 113 - 194 + 85;
        lIIllIll[26] = 121 ^ 111;
        lIIllIll[27] = 147 ^ 132;
        lIIllIll[28] = 233 ^ 198 ^ 89 ^ 110;
        lIIllIll[29] = 5 + 90 - 18 + 76 ^ 1 + 26 - -41 + 60;
        lIIllIll[30] = 24 + 51 - -26 + 64 ^ 152 + 17 - -8 + 14;
        lIIllIll[31] = 116 + 80 - 167 + 157 ^ 142 + 44 - 31 + 6;
        lIIllIll[32] = 85 ^ 73;
        lIIllIll[33] = 110 + 107 - 180 + 108 ^ 116 + 129 - 138 + 33;
        lIIllIll[34] = 33 ^ 115 ^ 110 ^ 34;
        lIIllIll[35] = 31 ^ 83 ^ 252 ^ 175;
        lIIllIll[36] = 124 ^ 92;
        lIIllIll[37] = 80 ^ 113;
        lIIllIll[38] = 158 ^ 188;
        lIIllIll[39] = 72 ^ 107;
        lIIllIll[40] = 79 ^ 107;
        lIIllIll[41] = 248 ^ 174 ^ 62 ^ 77;
        lIIllIll[42] = 227 ^ 197;
        lIIllIll[43] = 147 ^ 168 ^ 161 ^ 189;
        lIIllIll[44] = 20 ^ 60;
        lIIllIll[45] = 24 ^ 49;
        lIIllIll[46] = 89 + 17 - -24 + 6 ^ 116 + 20 - 2 + 28;
        lIIllIll[47] = 15 ^ 88 ^ 20 ^ 104;
        lIIllIll[48] = 201 ^ 164 ^ 236 ^ 173;
        lIIllIll[49] = 141 + 18 - 41 + 55 ^ 98 + 82 - 65 + 13;
        lIIllIll[50] = 159 ^ 177;
        lIIllIll[51] = 186 ^ 149;
        lIIllIll[52] = 52 + 119 - 163 + 123 ^ 22 + 104 - 5 + 58;
        lIIllIll[53] = 93 ^ 17 ^ 45 ^ 80;
        lIIllIll[54] = 59 ^ 9;
        lIIllIll[55] = 86 ^ 101;
        lIIllIll[56] = 118 + 73 - 72 + 44 ^ 106 + 83 - 136 + 98;
    }

    public void resetConfig() {
        Functions.deleteFolder(lIIllIIl[lIIllIll[53]]);
        Functions.deleteFolder(lIIllIIl[lIIllIll[54]]);
    }

    public ItemStack getItem() {
        ItemBuilder var10000 = new ItemBuilder(Material.STONE);
        ChatColor var10001 = llIIlIllIlIlIll.getType().getColor();
        return var10000.setName("" + var10001 + llIIlIllIlIlIll.getDisplayName()).addLoreLine(lIIllIIl[lIIllIll[8]]).addLoreLine(lIIllIIl[lIIllIll[9]]).addLoreLine(lIIllIIl[lIIllIll[10]]).addLoreLine(lIIllIIl[lIIllIll[11]]).addLoreLine(lIIllIIl[lIIllIll[7]]).addLoreLine(lIIllIIl[lIIllIll[12]]).addLoreLine("  §7Typ§8: " + llIIlIllIlIlIll.getType().getDisplayName()).addLoreLine("  " + llIIlIllIlIlIll.getStatus().getDisplayName()).addLoreLine(lIIllIIl[lIIllIll[3]]).addLoreLine(llIIlIllIlIlIll.getType().getColor() + "§oKlick§8: §9Status ändern").toItemStack();
    }

    public void resetMod() {
        int[] var10001 = new int[lIIllIll[8]];
        var10001[lIIllIll[0]] = lIIllIll[19];
        var10001[lIIllIll[1]] = lIIllIll[20];
        var10001[lIIllIll[2]] = lIIllIll[0];
        var10001[lIIllIll[4]] = lIIllIll[0];
        llIIlIlIlIIlllI.coordinates = var10001;
        var10001 = new int[lIIllIll[8]];
        var10001[lIIllIll[0]] = lIIllIll[19];
        var10001[lIIllIll[1]] = lIIllIll[20];
        var10001[lIIllIll[2]] = lIIllIll[0];
        var10001[lIIllIll[4]] = lIIllIll[0];
        llIIlIlIlIIlllI.coordinates_nether = var10001;
        llIIlIlIlIIlllI.coordinates[lIIllIll[0]] = (Integer) llIIlIlIlIIlllI.resetValue(lIIllIIl[lIIllIll[37]], lIIllIll[19]);
        llIIlIlIlIIlllI.coordinates[lIIllIll[1]] = (Integer) llIIlIlIlIIlllI.resetValue(lIIllIIl[lIIllIll[38]], lIIllIll[20]);
        llIIlIlIlIIlllI.coordinates[lIIllIll[2]] = (Integer) llIIlIlIlIIlllI.resetValue(lIIllIIl[lIIllIll[39]], lIIllIll[0]);
        llIIlIlIlIIlllI.coordinates[lIIllIll[4]] = (Integer) llIIlIlIlIIlllI.resetValue(lIIllIIl[lIIllIll[40]], lIIllIll[0]);
        llIIlIlIlIIlllI.coordinates_nether[lIIllIll[0]] = (Integer) llIIlIlIlIIlllI.resetValue(lIIllIIl[lIIllIll[41]], lIIllIll[19]);
        llIIlIlIlIIlllI.coordinates_nether[lIIllIll[1]] = (Integer) llIIlIlIlIIlllI.resetValue(lIIllIIl[lIIllIll[42]], lIIllIll[20]);
        llIIlIlIlIIlllI.coordinates_nether[lIIllIll[2]] = (Integer) llIIlIlIlIIlllI.resetValue(lIIllIIl[lIIllIll[43]], lIIllIll[0]);
        llIIlIlIlIIlllI.coordinates_nether[lIIllIll[4]] = (Integer) llIIlIlIlIIlllI.resetValue(lIIllIIl[lIIllIll[44]], lIIllIll[0]);
    }

    private static boolean lllllIIll(int var0, int var1) {
        return var0 > var1;
    }

    public void enable() {
        Bukkit.getPluginManager().registerEvents(llIIlIllllllIlI, Main.getPlugin(Main.class));
        if (llllIlllI(llIIlIllllllIlI.isEnabled())) {
            llIIlIllllllIlI.generateWorld();
        }

    }

    private static boolean lllllIlll(int var0, int var1) {
        return var0 <= var1;
    }

    private void generateWorld() {
        WorldCreator worldCreator = new WorldCreator(lIIllIIl[lIIllIll[13]]);
        worldCreator.generator(new EmptyChunkGenerator(llIIlIllIIlIIll));

        llIIlIllIIlIIll.empty = worldCreator.createWorld();
        WorldCreator nether_worldCreator = new WorldCreator(lIIllIIl[lIIllIll[14]]);
        nether_worldCreator.environment(World.Environment.NETHER);

        nether_worldCreator.generator(new EmptyChunkGenerator(llIIlIllIIlIIll));

        nether_worldCreator.generateStructures((boolean) lIIllIll[1]);

        llIIlIllIIlIIll.empty_nether = nether_worldCreator.createWorld();
    }

    private static boolean lllllIlIl(int var0) {
        return var0 < 0;
    }

    private static class EmptyChunkGenerator extends ChunkGenerator {
        private EmptyChunkGenerator(OneLine oneLine) {
            lIIlIIlIIlIlIlI.oneLine = oneLine;
        }

        public ChunkGenerator.@NotNull ChunkData generateChunkData(@NotNull World lIIlIIlIIIllllI, @NotNull Random lIIlIIlIIIlllIl, int lIIlIIlIIIllIll, int lIIlIIlIIIllIIl, ChunkGenerator.@NotNull BiomeGrid lIIlIIlIIIllIII) {
            return lIIlIIlIIIlllll.createChunkData(lIIlIIlIIIllllI);
        }
    }
}*/