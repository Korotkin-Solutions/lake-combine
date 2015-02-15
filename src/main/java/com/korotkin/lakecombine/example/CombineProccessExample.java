/**
 * 
 * The MIT License (MIT) | Copyright (c) 2015 Korotkin <info@korotkin.co.il>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.korotkin.lakecombine.example;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.korotkin.lakecombine.common.CombineProcess;

public class CombineProccessExample extends CombineProcess {
	protected Logger logger = LoggerFactory.getLogger(CombineProccessExample.class);

	public void execute(Iterator<?> data) {
		logger.debug("Execute data");
		while (data.hasNext()) {
			// RETRIVE
			String datain = (String)data.next();
			updateStatus(PROCESS_STATUSES.RETRIVE, datain);

			updateStatus(PROCESS_STATUSES.PROCESS_START, datain);

			int max = 3;
			for (int i = 0; i < max; i++) {
				updateStatus(PROCESS_STATUSES.PROCESS_UPDATE, i + " of " + max);
				try {
					Thread.sleep((int) (Math.random() * 100));
				} catch (InterruptedException e) {}
			}

			updateStatus(PROCESS_STATUSES.PROCESS_DONE, datain);
		}
	}

	public void updateStatus(PROCESS_STATUSES status, Object item) {
		logger.debug("stt [" + status.name() + "]: " + item);
	}

}
