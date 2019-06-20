package com.adidas.sessions.dto.response;

import com.adidas.sessions.dto.SessionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionsListResponse {

    private List<SessionDTO> sessions;
}
