package net.benfro.commons.midi;

import com.google.common.collect.Lists;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import java.util.List;

public class MIDIConstants {

    public static final byte SYSEX_START = (byte) 0xF0;
    public static final byte SYSEX_END = (byte) 0xF7;

    public static List<MidiDevice.Info> getMidiDeviceInfo() {
        return Lists.newArrayList(MidiSystem.getMidiDeviceInfo());
    }

    public static List<MidiDevice> getMidiDevices() {
        List<MidiDevice> out = Lists.newArrayList();
        getMidiDeviceInfo().forEach(info -> {
            try {
                MidiDevice device = MidiSystem.getMidiDevice(info);
                out.add(device);
            } catch (MidiUnavailableException e) {
                System.err.println(e);
            }
        });
        return out;
    }

    public static List<Synthesizer> getSynths() {
        List<Synthesizer> out = Lists.newArrayList();
        getMidiDevices().forEach(info -> {
            if (info instanceof Synthesizer) {
                out.add((Synthesizer)info);
            }
        });
        return out;
    }

    private MIDIConstants() {
    }
}
