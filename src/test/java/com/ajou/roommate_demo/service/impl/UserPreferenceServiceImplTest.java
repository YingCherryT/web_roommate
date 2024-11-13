package com.ajou.roommate_demo.service.impl;

import com.ajou.roommate_demo.dto.PreferenceValueDTO;
import com.ajou.roommate_demo.dto.UserPreferenceDTO;
import com.ajou.roommate_demo.exception.PreferenceValueNotFoundException;
import com.ajou.roommate_demo.exception.UserNotFoundException;
import com.ajou.roommate_demo.model.PreferenceValue;
import com.ajou.roommate_demo.model.PreferenceType;
import com.ajou.roommate_demo.model.User;
import com.ajou.roommate_demo.model.UserPreference;
import com.ajou.roommate_demo.repository.PreferenceTypeRepository;
import com.ajou.roommate_demo.repository.PreferenceValueRepository;
import com.ajou.roommate_demo.repository.UserPreferenceRepository;
import com.ajou.roommate_demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserPreferenceServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserPreferenceRepository userPreferenceRepository;

    @Mock
    private PreferenceValueRepository preferenceValueRepository;

    @Mock
    private PreferenceTypeRepository preferenceTypeRepository;

    @InjectMocks
    private UserPreferenceServiceImpl userPreferenceService;

    private User user;
    private PreferenceValue preferenceValue;
    private PreferenceType preferenceType;
    private UserPreference userPreference;
    private List<UserPreference> userPreferences;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // 初始化用户对象
        user = new User();
        user.setUserId(1);
        user.setUsername("testUser");

        // 初始化偏好类型对象
        preferenceType = new PreferenceType();
        preferenceType.setPreferenceTypeId(1);
        preferenceType.setTypeName("Noise Level");

        // 初始化偏好值对象
        preferenceValue = new PreferenceValue();
        preferenceValue.setPreferenceValueId(1);
        preferenceValue.setPreferenceValue("Quiet");
        preferenceValue.setPreferenceType(preferenceType); // 设置偏好值关联的偏好类型

        // 初始化用户偏好对象
        userPreference = new UserPreference();
        userPreference.setUser(user);
        userPreference.setPreferenceValue(preferenceValue);

        // 模拟返回的用户偏好列表
        userPreferences = Arrays.asList(userPreference);

        // 模拟偏好类型仓库返回
        when(preferenceTypeRepository.findAll()).thenReturn(Arrays.asList(preferenceType));

        // 模拟用户偏好仓库的返回
        when(userPreferenceRepository.findByUser_UserId(1)).thenReturn(userPreferences);
    }

    // 测试 getUserPreferences 方法
    @Test
    public void testGetUserPreferences() {
        // 创建 PreferenceValue 对象
        PreferenceValue preferenceValue1 = new PreferenceValue();
        preferenceValue1.setPreferenceValue("Quiet");  // 设置值
        PreferenceType preferenceType1 = new PreferenceType(1, "Noise Level"); // 设置偏好类型
        preferenceValue1.setPreferenceType(preferenceType1); // 将偏好类型与偏好值关联

        // 创建另一个 PreferenceValue 对象
        PreferenceValue preferenceValue2 = new PreferenceValue();
        preferenceValue2.setPreferenceValue("Hot");  // 设置值
        PreferenceType preferenceType2 = new PreferenceType(2, "Temperature"); // 设置偏好类型
        preferenceValue2.setPreferenceType(preferenceType2); // 将偏好类型与偏好值关联

        // 创建 UserPreference 对象
        UserPreference userPreference1 = new UserPreference();
        userPreference1.setPreferenceValue(preferenceValue1);
        userPreference1.setPreferenceType(preferenceType1);

        // 创建第二个 UserPreference 对象
        UserPreference userPreference2 = new UserPreference();
        userPreference2.setPreferenceValue(preferenceValue2);
        userPreference2.setPreferenceType(preferenceType2);

        // 创建模拟的 UserPreference 列表
        List<UserPreference> mockPreferences = Arrays.asList(userPreference1, userPreference2);

        // 模拟 repository 的返回值，返回 List<UserPreference> 类型
        when(userPreferenceRepository.findByUser_UserId(1)).thenReturn(mockPreferences);

        // 调用服务方法
        List<UserPreferenceDTO> result = userPreferenceService.getUserPreferences(1);

        // 验证返回值
        assertNotNull(result);          // 确保结果不为 null
        assertEquals(2, result.size()); // 验证结果的大小为 2

        // 验证每个元素的内容
        assertEquals(1, result.get(0).getPreferenceTypeId());
        assertEquals("Noise Level", result.get(0).getTypeName());
        assertEquals(2, result.get(1).getPreferenceTypeId());
        assertEquals("Temperature", result.get(1).getTypeName());
    }



    // 测试 getUserPreferences 方法，当没有偏好值时
    @Test
    public void testGetUserPreferences_NoPreferences() {
        // 模拟没有偏好记录
        when(userPreferenceRepository.findByUser_UserId(1)).thenReturn(Collections.emptyList());

        // 创建并模拟返回的偏好类型
        PreferenceType preferenceType = new PreferenceType();
        preferenceType.setPreferenceTypeId(1);
        preferenceType.setTypeName("Noise Level");

        // 模拟返回偏好类型
        when(preferenceTypeRepository.findAll()).thenReturn(Arrays.asList(preferenceType));

        // 调用 getUserPreferences 方法
        List<UserPreferenceDTO> result = userPreferenceService.getUserPreferences(1);

        // 验证返回的结果是否符合预期
        assertNotNull(result);  // 确保返回结果不为 null
        assertEquals(1, result.size());  // 验证返回的列表大小

        // 验证返回的 UserPreferenceDTO 内容
        UserPreferenceDTO userPreferenceDTO = result.get(0);
        assertEquals(1, userPreferenceDTO.getPreferenceTypeId());  // 验证偏好类型 ID
        assertEquals("Noise Level", userPreferenceDTO.getTypeName());  // 验证偏好类型名称

        // 确认 findAll 被调用
        verify(preferenceTypeRepository, times(1)).findAll();  // 验证 findAll 是否被调用一次
    }

    // 测试 saveOrUpdateUserPreferences 方法
    @Test
    public void testSaveOrUpdateUserPreferences() {
        // 模拟用户存在
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        // 模拟偏好值存在
        when(preferenceValueRepository.findById(1)).thenReturn(Optional.of(preferenceValue));

        // 创建新的偏好值 ID 列表
        List<Integer> preferenceValueIds = Arrays.asList(1);

        // 调用 saveOrUpdateUserPreferences 方法
        userPreferenceService.saveOrUpdateUserPreferences(1, preferenceValueIds);

        // 验证 repository 方法是否被调用
        verify(userRepository, times(1)).findById(1);
        verify(preferenceValueRepository, times(1)).findById(1);

        // 验证 save 方法是否被调用
        ArgumentCaptor<UserPreference> captor = ArgumentCaptor.forClass(UserPreference.class);
        verify(userPreferenceRepository, times(1)).save(captor.capture());

        // 捕获的对象进行验证
        UserPreference savedPreference = captor.getValue();
        assertNotNull(savedPreference);
        assertEquals(user, savedPreference.getUser());
        assertEquals(preferenceValue, savedPreference.getPreferenceValue());
    }

    // 测试 saveOrUpdateUserPreferences 方法，当用户不存在时抛出异常
    @Test
    public void testSaveOrUpdateUserPreferences_UserNotFound() {
        // 模拟用户不存在
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        // 创建新的偏好值 ID 列表
        List<Integer> preferenceValueIds = Arrays.asList(1);

        // 验证当用户不存在时抛出 UserNotFoundException
        UserNotFoundException thrown = assertThrows(UserNotFoundException.class, () -> {
            userPreferenceService.saveOrUpdateUserPreferences(1, preferenceValueIds);
        });

        // 确认异常消息
        assertEquals("用户不存在，ID: 1", thrown.getMessage());
    }

    // 测试 saveOrUpdateUserPreferences 方法，当偏好值不存在时抛出异常
    @Test
    public void testSaveOrUpdateUserPreferences_PreferenceValueNotFound() {
        // 模拟用户存在
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        // 模拟偏好值不存在
        when(preferenceValueRepository.findById(1)).thenReturn(Optional.empty());

        // 创建新的偏好值 ID 列表
        List<Integer> preferenceValueIds = Arrays.asList(1);

        // 验证当偏好值不存在时抛出 PreferenceValueNotFoundException
        PreferenceValueNotFoundException thrown = assertThrows(PreferenceValueNotFoundException.class, () -> {
            userPreferenceService.saveOrUpdateUserPreferences(1, preferenceValueIds);
        });

        // 确认异常消息
        assertEquals("偏好值不存在，ID: 1", thrown.getMessage());
    }

    // 测试 saveOrUpdateUserPreferences 方法，用户有多个偏好
    @Test
    public void testSaveOrUpdateUserPreferences_MultiplePreferences() {
        // 模拟用户存在
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        // 模拟多个偏好值
        PreferenceValue preferenceValue2 = new PreferenceValue();
        preferenceValue2.setPreferenceValueId(2);
        preferenceValue2.setPreferenceValue("Loud");
        preferenceValue2.setPreferenceType(preferenceType);

        when(preferenceValueRepository.findById(1)).thenReturn(Optional.of(preferenceValue));
        when(preferenceValueRepository.findById(2)).thenReturn(Optional.of(preferenceValue2));

        // 创建多个偏好值 ID 列表
        List<Integer> preferenceValueIds = Arrays.asList(1, 2);

        // 调用 saveOrUpdateUserPreferences 方法
        userPreferenceService.saveOrUpdateUserPreferences(1, preferenceValueIds);

        // 验证 save 方法是否被调用两次
        ArgumentCaptor<UserPreference> captor = ArgumentCaptor.forClass(UserPreference.class);
        verify(userPreferenceRepository, times(2)).save(captor.capture());

        // 捕获的对象进行验证
        List<UserPreference> savedPreferences = captor.getAllValues();
        assertNotNull(savedPreferences);
        assertEquals(2, savedPreferences.size());

        UserPreference savedPreference1 = savedPreferences.get(0);
        assertEquals(user, savedPreference1.getUser());
        assertEquals(preferenceValue, savedPreference1.getPreferenceValue());

        UserPreference savedPreference2 = savedPreferences.get(1);
        assertEquals(user, savedPreference2.getUser());
        assertEquals(preferenceValue2, savedPreference2.getPreferenceValue());
    }
}
