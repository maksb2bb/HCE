package com.example.nfc3

import android.nfc.cardemulation.HostApduService
import android.os.Bundle

class HceService : HostApduService() {
    private val OK         = byteArrayOf(0x90.toByte(), 0x00.toByte())
    private val FAILED     = byteArrayOf(0x6F.toByte(), 0x00.toByte())
    private val NOT_FOUND  = byteArrayOf(0x6A.toByte(), 0x82.toByte())

    override fun processCommandApdu(commandApdu: ByteArray?, extras: Bundle?): ByteArray {
        if (commandApdu == null || commandApdu.size < 4) return FAILED
        val cla = commandApdu[0].toInt() and 0xFF
        val ins = commandApdu[1].toInt() and 0xFF
        val p1  = commandApdu[2].toInt() and 0xFF

        // SELECT AID (00 A4 04 00 ...)
        if (cla == 0x00 && ins == 0xA4 && p1 == 0x04) {
            val bytes = NumberHolder.value.encodeToByteArray()
            return bytes + OK
        }
        return NOT_FOUND
    }
    override fun onDeactivated(reason: Int) {}
}
