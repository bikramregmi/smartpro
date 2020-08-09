package com.rubic.smartpro.service.impl;

import com.rubic.smartpro.domain.Company;
import com.rubic.smartpro.repository.CompanyRepository;
import com.rubic.smartpro.service.GroupService;
import com.rubic.smartpro.domain.Group;
import com.rubic.smartpro.repository.GroupRepository;
import com.rubic.smartpro.service.dto.GroupDTO;
import com.rubic.smartpro.service.mapper.GroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Group}.
 */
@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    private final Logger log = LoggerFactory.getLogger(GroupServiceImpl.class);

    private final GroupRepository groupRepository;

    private final GroupMapper groupMapper;

    private final CompanyRepository companyRepository;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper, CompanyRepository companyRepository) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.companyRepository = companyRepository;
    }

    /**
     * Save a group.
     *
     * @param groupDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GroupDTO save(GroupDTO groupDTO) {
        Optional<Company> company=companyRepository.findSelectedCompanyByExtraField("true");
        log.debug("Request to save Group : {}", groupDTO);
        Group group = groupMapper.toEntity(groupDTO);
        group.setCompany(company.get());
        group = groupRepository.save(group);
        return groupMapper.toDto(group);
    }

    /**
     * Get all the groups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GroupDTO> findAll(Pageable pageable) {
        Optional<Company> company=companyRepository.findSelectedCompanyByExtraField("true");
        log.debug("Request to get all Groups");
        return groupRepository.findAllByCompanyId(pageable,company.get().getId())
            .map(groupMapper::toDto);
    }


    /**
     * Get one group by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GroupDTO> findOne(Long id) {
        log.debug("Request to get Group : {}", id);
        return groupRepository.findById(id)
            .map(groupMapper::toDto);
    }

    /**
     * Delete the group by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Group : {}", id);
        groupRepository.deleteById(id);
    }
}
