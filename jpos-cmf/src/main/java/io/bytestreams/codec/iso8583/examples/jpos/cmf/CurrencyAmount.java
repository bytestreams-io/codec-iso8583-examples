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
  private String currencyCode;
  private int decimalPlaces;
  private long amount;

  public CurrencyAmount(String decoded) {
    currencyCode = decoded.substring(0, 3);
    decimalPlaces = Integer.parseInt(decoded.substring(3, 4));
    amount = Long.parseLong(decoded.substring(4));
  }

  public static Codec<CurrencyAmount> codec(int length) {
    return new CurrencyAmountCodec(length);
  }

  private String encode(String formatString) {
    return String.format(formatString, currencyCode, decimalPlaces, amount);
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

  private static class CurrencyAmountCodec
      implements Codec<CurrencyAmount>, Inspectable<CurrencyAmount> {
    private final Codec<CurrencyAmount> delegate;

    public CurrencyAmountCodec(int length) {
      String format = "%s%1d%0" + (length - 4) + "d";
      delegate = Codecs.hex(length).xmap(CurrencyAmount::new, amount -> amount.encode(format));
    }

    @Override
    public EncodeResult encode(CurrencyAmount value, OutputStream output) throws IOException {
      return delegate.encode(value, output);
    }

    @Override
    public CurrencyAmount decode(InputStream input) throws IOException {
      return delegate.decode(input);
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
