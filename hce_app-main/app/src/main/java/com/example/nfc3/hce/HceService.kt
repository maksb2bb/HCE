package com.example.nfc3.hce

import android.nfc.cardemulation.HostApduService
import android.os.Bundle

class HceService : HostApduService() {

    private val STATUS_OK        = byteArrayOf(0x90.toByte(), 0x00.toByte())
    private val STATUS_FAILED    = byteArrayOf(0x6F.toByte(), 0x00.toByte())
    private val STATUS_NOT_FOUND = byteArrayOf(0x6A.toByte(), 0x82.toByte())

    override fun processCommandApdu(commandApdu: ByteArray?, extras: Bundle?): ByteArray {
        if (commandApdu == null || commandApdu.size < 4) return STATUS_FAILED

        val cla = commandApdu[0].toInt() and 0xFF
        val ins = commandApdu[1].toInt() and 0xFF
        val p1  = commandApdu[2].toInt() and 0xFF

        // Обрабатываем SELECT by AID: 00 A4 04 00 Lc AID...
        if (cla == 0x00 && ins == 0xA4 && p1 == 0x04) {
            val bytes = NumberHolder.value.encodeToByteArray() // ASCII-число
            return bytes + STATUS_OK
        }

        // Неизвестная/другая команда
        return STATUS_NOT_FOUND
    }

    override fun onDeactivated(reason: Int) {
        // Можно логировать reason при желании
    }
}
