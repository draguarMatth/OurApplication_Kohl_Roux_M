package com.example.ourapplication_kohl_roux_m.util;

/**
 * This generic interface is used as custom callback for async tasks.
 * For an example usage see {@link ch.hevs.aislab.intro.ui.ClientDetails:197}.
 */
public interface OnAsyncEventListener {
    void onSuccess();
    void onFailure(Exception e);
}
