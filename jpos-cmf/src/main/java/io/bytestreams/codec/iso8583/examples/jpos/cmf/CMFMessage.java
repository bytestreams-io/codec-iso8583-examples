package io.bytestreams.codec.iso8583.examples.jpos.cmf;

import io.bytestreams.codec.core.DataObject;
import io.bytestreams.codec.iso8583.Bitmapped;
import io.bytestreams.codec.iso8583.MultiBlockBitmap;

public class CMFMessage extends DataObject implements Bitmapped {
  private final MultiBlockBitmap bitmap = new MultiBlockBitmap(8);

  @Override
  public MultiBlockBitmap getBitmap() {
    return bitmap;
  }
}
