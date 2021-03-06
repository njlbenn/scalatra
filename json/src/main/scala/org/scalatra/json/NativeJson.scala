package org.scalatra
package json

import text.Document
import org.json4s._
import java.io.{InputStreamReader, InputStream, Writer}


trait NativeJsonSupport extends JsonSupport[Document] with NativeJsonOutput with JValueResult {
  protected def readJsonFromStreamWithCharset(stream: InputStream, charset: String): JValue =
    native.JsonParser.parse(new InputStreamReader(stream, charset))

  protected def readJsonFromBody(bd: String): JValue = native.JsonParser.parse(bd)
}

trait NativeJsonValueReaderProperty extends JsonValueReaderProperty[Document] { self: native.JsonMethods => }


trait NativeJsonOutput extends JsonOutput[Document] with native.JsonMethods {
  protected def writeJson(json: JValue, writer: Writer) {
    native.Printer.compact(render(json), writer)
  }
}


