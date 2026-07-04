# Sprint 2 — Authentication System

**Date:** 06-06-2026
**Goal:** Register + Login + JWT working

## Files Created
| File | Purpose                |
|---|------------------------|
| User.java | Database entity        |
| RegisterRequest.java | Register input         |
| LoginRequest.java | Login input            |
| AuthResponse.java | Login output           |
| UserRepository.java | DB operations          |
| JwtUtil.java | Token create/validate  |
| UserDetailsServiceImpl.java | Spring Security helper |
| JwtAuthFilter.java | Request interceptor    |
| SecurityConfig.java | Security rules         |
| AuthService.java | Business logic         |
| AuthController.java | APIs                   |
| GlobalExceptionHandler.java | Error handling         |
| InvalidCredentialsException.java | Custom exception       |

## APIs Tested
| API | Status | Result |
|---|---|---|
| POST /register | 201 | ✅ |
| POST /login | 200 + JWT | ✅ |
| Wrong password | 401 | ✅ |
| Empty password | 400 | ✅ |
| Duplicate email | 409 | ✅ |

## Status: ✅ COMPLETE