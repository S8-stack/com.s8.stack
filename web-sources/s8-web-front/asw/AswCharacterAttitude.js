
import { AswCharacter } from '/s8-web-front/asw/AswCharacter.js';
import { S8 } from '/s8-io-bohr-atom/S8.js';
import { NeObject } from '/s8-io-bohr-neon/NeObject.js';

/**
 * 
 */
S8.import_CSS('/s8-web-front/asw/Asw.css');


export const VOICE_SYNTH = window.speechSynthesis;

/**
 * 
 */
export class AswCharacterAttitude extends NeObject {


    /**
     * @type {AswCharacter}
     */
    character;

    /**
     * @type {string}
     */
    voiceName = "Aurelie";

    /**
     * @type {SpeechSynthesisVoice}
     */
    voice;

    /**
     * @type {number}
     */
    speechPitch = 1.2;
    
    /**
     * @type {number}
     */
    speechRate = 0.9;
    
    /**
     * @type {number}
     */
    speechVolume = 1.2;
    
    /**
     * @type {number}
     */
    speechPause = 250;


    /**
     * @type {string}
     */
    faceImagePathname;

    /**
     * @type {Image}
     */
    faceImage;


    isFaceLoaded = false;
    isVoiceLoaded = false;
    isLoaded = false;


    constructor() {
        super();
    }

    repaint() {
        //this.wrapperNode.style.width = `${this.viewportWidth}%`;
        this.wrapperNode.style.top = `${this.viewportY}%`;
        this.wrapperNode.style.left = `${this.viewportX}%`;


        this.faceImageNode.src = this.faceImages[this.faceIndex].src;
    }

    getEnvelope() {
        return this.wrapperNode;
    }


    /**
     * 
     * @param {*[]} elements 
     */
    S8_set_elements(elements) {
        elements.forEach(element => {
            if (element != null) {
                this.wrapperNode.appendChild(element.getEnvelope());
            }
        });
    }


    S8_set_speechPitch(value) {
        this.speechPitch = value;
    }

    S8_set_speechRate(value) {
        this.speechRate = value;
    }

    S8_set_speechVolume(value) {
        this.speechVolume = value;
    }

    S8_set_speechPause(value) {
        this.speechPause = value;
    }


    S8_set_voiceName(value) {
        this.voiceName = value;
        
    }

    S8_set_faceImagePathname(pathname) {
        this.faceImagePathname = pathname;
    }


    load(){
        if(!this.isLoaded){
            let _this = this;
            this.faceImage = new Image();
            this.faceImage.onload = function() {
                _this.isFaceLoaded = true;
                _this.notifyLoadStatus();
            }
            this.faceImage.src = this.faceImagePathname;

            
            setTimeout(() => {
                this.voice = VOICE_SYNTH_findVoice(this.voiceName);
                _this.isVoiceLoaded = true;
                _this.notifyLoadStatus();
            }, 64);
        }
    }

    notifyLoadStatus(){
        if(this.isFaceLoaded && this.isVoiceLoaded){
            this.isLoaded = true;
            this.character.notifyAttitudeLoaded();
        }
    }



    S8_set_faceIndex(index) {
        this.faceIndex = index;
    }

    S8_render() {
        /* continuous rendering approach... */
    }


    S8_dispose() { /* no disposing to be done... */ }


    say(text, pause, onSaid){
        let utterance = new SpeechSynthesisUtterance(text);
        utterance.voice = this.voice;
        utterance.pitch = this.speechPitch;
        utterance.rate = this.speechRate;
        utterance.volume = this.speechVolume;
       
        utterance.onend = function () {

            let interval = setInterval(function () {

                clearInterval(interval);

                onSaid();
            }, pause);
        };

        VOICE_SYNTH.speak(utterance);
    }

}





/**
 * 
 * @returns s
 */
const VOICE_SYNTH_findVoice = function (selectedVoiceName) {
    let voices = VOICE_SYNTH.getVoices();
    for (let i = 0; i < voices.length; i++) {
        let voice = voices[i];
        let name = voice.name;
        let language = voice.lang;

        if (name == selectedVoiceName) {
            return voice;
        }
    }
}


