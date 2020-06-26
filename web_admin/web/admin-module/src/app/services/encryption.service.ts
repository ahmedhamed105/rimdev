import { Injectable } from '@angular/core';
import * as CryptoJS from 'crypto-js';

@Injectable({
  providedIn: 'root'
})
export class EncryptionService {


  constructor() { }
  keySize;
  iterationCount;

AesUtil(keySize, iterationCount) {
  this.keySize = keySize / 32;
  this.iterationCount = iterationCount;
};
 
generateKey(salt, passPhrase) {
  var key = CryptoJS.PBKDF2(
      passPhrase, 
      CryptoJS.enc.Hex.parse(salt),
      { keySize: this.keySize, iterations: this.iterationCount });
  return key;
}
 
encrypt(salt, iv, passPhrase, plainText) {
  var key = this.generateKey(salt, passPhrase);
  var encrypted = CryptoJS.AES.encrypt(
      plainText,
      key,
      { iv: CryptoJS.enc.Hex.parse(iv) });
  return encrypted.ciphertext.toString(CryptoJS.enc.Base64);
}
 
decrypt(salt, iv, passPhrase, cipherText) {
  var key = this.generateKey(salt, passPhrase);
  var cipherParams = CryptoJS.lib.CipherParams.create({
    ciphertext: CryptoJS.enc.Base64.parse(cipherText)
  });
  var decrypted = CryptoJS.AES.decrypt(
      cipherParams,
      key,
      { iv: CryptoJS.enc.Hex.parse(iv) });
  return decrypted.toString(CryptoJS.enc.Utf8);
}

makeid(length) {
  var result           = '';
  var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
  var charactersLength = characters.length;
  for ( var i = 0; i < length; i++ ) {
     result += characters.charAt(Math.floor(Math.random() * charactersLength));
  }
  return result;
}



public encypttext(text):any{
  if(text === 'Enter password'){
    return '';
  }
  var key =  this.makeid(16);
  var iv = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
  var salt = CryptoJS.lib.WordArray.random(128/8).toString(CryptoJS.enc.Hex);
  this.AesUtil(128, 1000);
  var ciphertext = this.encrypt(salt, iv, key,  text);
  var aesPassword = (iv + "::" + salt + "::" + ciphertext);
 // console.log(aesPassword)
 //  console.log(key)
   return btoa(aesPassword)+key;

}

}
