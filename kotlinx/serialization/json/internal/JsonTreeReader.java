package kotlinx.serialization.json.internal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.DeepRecursiveFunction;
import kotlin.DeepRecursiveKt;
import kotlin.DeepRecursiveScope;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class JsonTreeReader {
    private final boolean isLenient;
    @NotNull
    private final AbstractJsonLexer lexer;
    private int stackDepth;

    public JsonTreeReader(@NotNull JsonConfiguration configuration, @NotNull AbstractJsonLexer lexer) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(lexer, "lexer");
        this.lexer = lexer;
        this.isLenient = configuration.isLenient();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JsonElement readArray() {
        byte consumeNextToken = this.lexer.consumeNextToken();
        if (this.lexer.peekNextToken() == 4) {
            AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        ArrayList arrayList = new ArrayList();
        while (this.lexer.canConsumeValue()) {
            arrayList.add(read());
            consumeNextToken = this.lexer.consumeNextToken();
            if (consumeNextToken != 4) {
                AbstractJsonLexer abstractJsonLexer = this.lexer;
                boolean z = consumeNextToken == 9;
                int i2 = abstractJsonLexer.f12451a;
                if (!z) {
                    abstractJsonLexer.fail("Expected end of the array or comma", i2);
                    throw new KotlinNothingValueException();
                }
            }
        }
        if (consumeNextToken == 8) {
            this.lexer.consumeNextToken((byte) 9);
        } else if (consumeNextToken == 4) {
            AbstractJsonLexer.fail$default(this.lexer, "Unexpected trailing comma", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        return new JsonArray(arrayList);
    }

    private final JsonElement readDeepRecursive() {
        return (JsonElement) DeepRecursiveKt.invoke(new DeepRecursiveFunction(new JsonTreeReader$readDeepRecursive$1(this, null)), Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00d3  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x009f -> B:27:0x00a9). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object readObject(DeepRecursiveScope<Unit, JsonElement> deepRecursiveScope, Continuation<? super JsonElement> continuation) {
        JsonTreeReader$readObject$2 jsonTreeReader$readObject$2;
        Object coroutine_suspended;
        int i2;
        JsonTreeReader jsonTreeReader;
        LinkedHashMap linkedHashMap;
        Object obj;
        JsonTreeReader$readObject$2 jsonTreeReader$readObject$22;
        byte b2;
        DeepRecursiveScope<Unit, JsonElement> deepRecursiveScope2;
        if (continuation instanceof JsonTreeReader$readObject$2) {
            jsonTreeReader$readObject$2 = (JsonTreeReader$readObject$2) continuation;
            int i3 = jsonTreeReader$readObject$2.f12466g;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                jsonTreeReader$readObject$2.f12466g = i3 - Integer.MIN_VALUE;
                Object obj2 = jsonTreeReader$readObject$2.f12464e;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = jsonTreeReader$readObject$2.f12466g;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj2);
                    byte consumeNextToken = this.lexer.consumeNextToken((byte) 6);
                    if (this.lexer.peekNextToken() == 4) {
                        AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, 2, null);
                        throw new KotlinNothingValueException();
                    }
                    jsonTreeReader = this;
                    linkedHashMap = new LinkedHashMap();
                    obj = coroutine_suspended;
                    jsonTreeReader$readObject$22 = jsonTreeReader$readObject$2;
                    b2 = consumeNextToken;
                    deepRecursiveScope2 = deepRecursiveScope;
                    if (jsonTreeReader.lexer.canConsumeValue()) {
                    }
                    if (b2 != 6) {
                    }
                    return new JsonObject(linkedHashMap);
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    String str = (String) jsonTreeReader$readObject$2.f12463d;
                    linkedHashMap = (LinkedHashMap) jsonTreeReader$readObject$2.f12462c;
                    jsonTreeReader = (JsonTreeReader) jsonTreeReader$readObject$2.f12461b;
                    DeepRecursiveScope<Unit, JsonElement> deepRecursiveScope3 = (DeepRecursiveScope) jsonTreeReader$readObject$2.f12460a;
                    ResultKt.throwOnFailure(obj2);
                    jsonTreeReader$readObject$22 = jsonTreeReader$readObject$2;
                    Object obj3 = coroutine_suspended;
                    linkedHashMap.put(str, (JsonElement) obj2);
                    byte consumeNextToken2 = jsonTreeReader.lexer.consumeNextToken();
                    if (consumeNextToken2 != 4) {
                        obj = obj3;
                        b2 = consumeNextToken2;
                        deepRecursiveScope2 = deepRecursiveScope3;
                        if (jsonTreeReader.lexer.canConsumeValue()) {
                            String consumeStringLenient = jsonTreeReader.isLenient ? jsonTreeReader.lexer.consumeStringLenient() : jsonTreeReader.lexer.consumeString();
                            jsonTreeReader.lexer.consumeNextToken((byte) 5);
                            Unit unit = Unit.INSTANCE;
                            jsonTreeReader$readObject$22.f12460a = deepRecursiveScope2;
                            jsonTreeReader$readObject$22.f12461b = jsonTreeReader;
                            jsonTreeReader$readObject$22.f12462c = linkedHashMap;
                            jsonTreeReader$readObject$22.f12463d = consumeStringLenient;
                            jsonTreeReader$readObject$22.f12466g = 1;
                            Object callRecursive = deepRecursiveScope2.callRecursive(unit, jsonTreeReader$readObject$22);
                            if (callRecursive == obj) {
                                return obj;
                            }
                            deepRecursiveScope3 = deepRecursiveScope2;
                            obj2 = callRecursive;
                            Object obj4 = obj;
                            str = consumeStringLenient;
                            obj3 = obj4;
                            linkedHashMap.put(str, (JsonElement) obj2);
                            byte consumeNextToken22 = jsonTreeReader.lexer.consumeNextToken();
                            if (consumeNextToken22 != 4) {
                                if (consumeNextToken22 != 7) {
                                    AbstractJsonLexer.fail$default(jsonTreeReader.lexer, "Expected end of the object or comma", 0, 2, null);
                                    throw new KotlinNothingValueException();
                                }
                                b2 = consumeNextToken22;
                            }
                        }
                        if (b2 != 6) {
                            jsonTreeReader.lexer.consumeNextToken((byte) 7);
                        } else if (b2 == 4) {
                            AbstractJsonLexer.fail$default(jsonTreeReader.lexer, "Unexpected trailing comma", 0, 2, null);
                            throw new KotlinNothingValueException();
                        }
                        return new JsonObject(linkedHashMap);
                    }
                }
            }
        }
        jsonTreeReader$readObject$2 = new JsonTreeReader$readObject$2(this, continuation);
        Object obj22 = jsonTreeReader$readObject$2.f12464e;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = jsonTreeReader$readObject$2.f12466g;
        if (i2 != 0) {
        }
    }

    private final JsonElement readObject() {
        byte consumeNextToken = this.lexer.consumeNextToken((byte) 6);
        if (this.lexer.peekNextToken() == 4) {
            AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        while (true) {
            if (!this.lexer.canConsumeValue()) {
                break;
            }
            String consumeStringLenient = this.isLenient ? this.lexer.consumeStringLenient() : this.lexer.consumeString();
            this.lexer.consumeNextToken((byte) 5);
            linkedHashMap.put(consumeStringLenient, read());
            consumeNextToken = this.lexer.consumeNextToken();
            if (consumeNextToken != 4) {
                if (consumeNextToken != 7) {
                    AbstractJsonLexer.fail$default(this.lexer, "Expected end of the object or comma", 0, 2, null);
                    throw new KotlinNothingValueException();
                }
            }
        }
        if (consumeNextToken == 6) {
            this.lexer.consumeNextToken((byte) 7);
        } else if (consumeNextToken == 4) {
            AbstractJsonLexer.fail$default(this.lexer, "Unexpected trailing comma", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        return new JsonObject(linkedHashMap);
    }

    private final JsonObject readObjectImpl(Function0<? extends JsonElement> function0) {
        byte consumeNextToken = this.lexer.consumeNextToken((byte) 6);
        if (this.lexer.peekNextToken() == 4) {
            AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        while (true) {
            if (!this.lexer.canConsumeValue()) {
                break;
            }
            String consumeStringLenient = this.isLenient ? this.lexer.consumeStringLenient() : this.lexer.consumeString();
            this.lexer.consumeNextToken((byte) 5);
            linkedHashMap.put(consumeStringLenient, function0.invoke());
            consumeNextToken = this.lexer.consumeNextToken();
            if (consumeNextToken != 4) {
                if (consumeNextToken != 7) {
                    AbstractJsonLexer.fail$default(this.lexer, "Expected end of the object or comma", 0, 2, null);
                    throw new KotlinNothingValueException();
                }
            }
        }
        if (consumeNextToken == 6) {
            this.lexer.consumeNextToken((byte) 7);
        } else if (consumeNextToken == 4) {
            AbstractJsonLexer.fail$default(this.lexer, "Unexpected trailing comma", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        return new JsonObject(linkedHashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JsonPrimitive readValue(boolean z) {
        String consumeStringLenient = (this.isLenient || !z) ? this.lexer.consumeStringLenient() : this.lexer.consumeString();
        return (z || !Intrinsics.areEqual(consumeStringLenient, "null")) ? new JsonLiteral(consumeStringLenient, z) : JsonNull.INSTANCE;
    }

    @NotNull
    public final JsonElement read() {
        byte peekNextToken = this.lexer.peekNextToken();
        if (peekNextToken == 1) {
            return readValue(true);
        }
        if (peekNextToken == 0) {
            return readValue(false);
        }
        if (peekNextToken != 6) {
            if (peekNextToken == 8) {
                return readArray();
            }
            AbstractJsonLexer.fail$default(this.lexer, Intrinsics.stringPlus("Cannot begin reading element, unexpected token: ", Byte.valueOf(peekNextToken)), 0, 2, null);
            throw new KotlinNothingValueException();
        }
        int i2 = this.stackDepth + 1;
        this.stackDepth = i2;
        this.stackDepth--;
        return i2 == 200 ? readDeepRecursive() : readObject();
    }
}
