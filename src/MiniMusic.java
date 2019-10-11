import javax.sound.midi.*;




public class MiniMusic {

    public void play(){
        try {
            Synthesizer synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            Soundbank sb = synthesizer.getDefaultSoundbank();
            Instrument instruments[]= sb.getInstruments();
            MidiChannel[] channels = synthesizer.getChannels();
            MidiChannel channel = channels[0];
            int instrumentIndex = 42;

            Instrument in = instruments[instrumentIndex];
            Sequencer player = MidiSystem.getSequencer();
            player.open();

            synthesizer.loadInstrument(in);
            System.out.println(in.getName());
            //节拍器
            Sequence seq = new Sequence(Sequence.PPQ,16);
            Track track = seq.createTrack();
            ShortMessage first = new ShortMessage();
            first.setMessage(192,0,1,0);

            track.add(makeEvent(192,instrumentIndex,64,0));
            //channel.programChange(bass_index);
            channel.programChange(instrumentIndex);
            //channel.noteOn(50,64);
            int base = 48;
            int whiteId[] =  {0,2,4,5,7,9,11};
            int someMusic[] = {1,2,3,1,1,2,3,1,3,4,5,5,3,4,5,5};
            for(int i=0,j=0; i<60; i+=4,j++){

                //随即音符
                System.out.println(j);
                track.add(makeEvent(144, whiteId[someMusic[j]-1] + base, 100, i));
                track.add(makeEvent(128, whiteId[someMusic[j]-1] + base, 100, i+4));
                if(j==someMusic.length-1) {
                    j = 0;
                }
                System.out.println(j);

            }
            System.out.println("Here");
            player.setSequence(seq);

           // player.setLoopCount(player.LOOP_CONTINUOUSLY);    //指定无穷的重复次数
            player.setTempoInBPM(120);
            player.start();
        } catch (Exception e) {
            // TODO: handle exception

        }
    }

    public MidiEvent makeEvent(int comd, int num, int velocity, int tick){
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd,num,velocity);
            //表示在tick拍启动a这个Message
            event = new MidiEvent(a, tick);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }

    public static void main(String[] args) {
        MiniMusic mini = new MiniMusic();
        mini.play();

    }
}