import * as jspb from 'google-protobuf'



export class ParseRequest extends jspb.Message {
  getXml(): string;
  setXml(value: string): ParseRequest;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): ParseRequest.AsObject;
  static toObject(includeInstance: boolean, msg: ParseRequest): ParseRequest.AsObject;
  static serializeBinaryToWriter(message: ParseRequest, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): ParseRequest;
  static deserializeBinaryFromReader(message: ParseRequest, reader: jspb.BinaryReader): ParseRequest;
}

export namespace ParseRequest {
  export type AsObject = {
    xml: string,
  }
}

export class ParseResponse extends jspb.Message {
  getJson(): string;
  setJson(value: string): ParseResponse;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): ParseResponse.AsObject;
  static toObject(includeInstance: boolean, msg: ParseResponse): ParseResponse.AsObject;
  static serializeBinaryToWriter(message: ParseResponse, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): ParseResponse;
  static deserializeBinaryFromReader(message: ParseResponse, reader: jspb.BinaryReader): ParseResponse;
}

export namespace ParseResponse {
  export type AsObject = {
    json: string,
  }
}

