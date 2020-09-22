package test.decathlon.enums;

public enum Event {
    RUN_100(25.4347f, 18f, 1.81f, 1),
    LONG_JUM(90.5674f, 2.2f, 1.4f, 2),
    SHOT_PUT(51.39f, 1.5f, 1.05f, 3),
    HIGH_JUMP(585.64f, 0.75f, 1.42f, 4),
    RUN_400(1.53775f, 82, 1.81f, 5),
    HURDLES_110(5.74352f, 28.5f, 1.92f, 6),
    DISCUS_THROW(12.91f, 4f, 1.1f, 7),
    POLE_VAULT(140.182f, 1f, 1.35f, 8),
    JAVELIN_THROW(10.14f, 7f, 1.08f, 9),
    RUN_1500(0.03768f, 480f, 1.85f, 10);

    public final float a;
    public final float b;
    public final float c;

    public final int index;
    Event(float a, float b, float c, int index) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.index = index;
    }
}
