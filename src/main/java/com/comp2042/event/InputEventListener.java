package com.comp2042.event;

import com.comp2042.dto.DownData;
import com.comp2042.dto.ViewData;

public interface InputEventListener {

    DownData onDownEvent(MoveEvent event);

    ViewData onLeftEvent(MoveEvent event);

    ViewData onRightEvent(MoveEvent event);

    ViewData onRotateEvent(MoveEvent event);

    ViewData createNewGame();
}
