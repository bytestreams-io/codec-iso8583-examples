package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.Codec;
import io.bytestreams.codec.core.Codecs;
import io.bytestreams.codec.core.EncodeResult;
import io.bytestreams.codec.core.Inspectable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

public class CurrencyAmount {
  public static final Codec<CurrencyAmount> CODEC = new TransactionAmountCodec();
  private String currencyCode;
  private int decimalPlaces;
  private long amount;

  public CurrencyAmount(String decoded) {
    currencyCode = decoded.substring(0, 3);
    decimalPlaces = Integer.parseInt(decoded.substring(3, 4));
    amount = Long.parseLong(decoded.substring(4));
  }

  private String encode() {
    return String.format("%s%1d%012d", currencyCode, decimalPlaces, amount);
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public int getDecimalPlaces() {
    return decimalPlaces;
  }

  public void setDecimalPlaces(int decimalPlaces) {
    this.decimalPlaces = decimalPlaces;
  }

  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }

  public static class TransactionAmountCodec
      implements Codec<CurrencyAmount>, Inspectable<CurrencyAmount> {
    private static final Codec<CurrencyAmount> DELEGATE =
        Codecs.hex(16).xmap(CurrencyAmount::new, CurrencyAmount::encode);

    @Override
    public EncodeResult encode(CurrencyAmount value, OutputStream output) throws IOException {
      return DELEGATE.encode(value, output);
    }

    @Override
    public CurrencyAmount decode(InputStream input) throws IOException {
      return DELEGATE.decode(input);
    }

    @Override
    public Object inspect(CurrencyAmount value) {
      return Map.of(
          "currencyCode",
          value.getCurrencyCode(),
          "decimalPlaces",
          value.getDecimalPlaces(),
          "amount",
          value.getAmount());
    }
  }
}
