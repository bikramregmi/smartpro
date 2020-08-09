package com.rubic.smartpro.service.impl;

import com.rubic.smartpro.domain.Company;
import com.rubic.smartpro.repository.CompanyRepository;
import com.rubic.smartpro.service.GroupsService;
import com.rubic.smartpro.domain.Groups;
import com.rubic.smartpro.repository.GroupsRepository;
import com.rubic.smartpro.service.dto.GroupsDTO;
import com.rubic.smartpro.service.mapper.GroupsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Groups}.
 */
@Service
@Transactional
public class GroupsServiceImpl implements GroupsService {

    private final Logger log = LoggerFactory.getLogger(GroupsServiceImpl.class);

    private final GroupsRepository groupsRepository;

    private final GroupsMapper groupsMapper;

    private final CompanyRepository companyRepository;

    public GroupsServiceImpl(GroupsRepository groupsRepository, GroupsMapper groupsMapper, CompanyRepository companyRepository) {
        this.groupsRepository = groupsRepository;
        this.groupsMapper = groupsMapper;
        this.companyRepository = companyRepository;
    }

    /**
     * Save a groups.
     *
     * @param groupsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GroupsDTO save(GroupsDTO groupsDTO) {
        Optional<Company> company=companyRepository.findSelectedCompanyByExtraField("true");
        log.debug("Request to save Groups : {}", groupsDTO);
        Groups groups = groupsMapper.toEntity(groupsDTO);
        groups.setCompany(company.get());
        groups = groupsRepository.save(groups);
        return groupsMapper.toDto(groups);
    }

    /**
     * Get all the groups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GroupsDTO> findAll(Pageable pageable) {
        Optional<Company> company=companyRepository.findSelectedCompanyByExtraField("true");
        log.debug("Request to get all Groups");
        return groupsRepository.findAllByCompanyId(pageable,company.get().getId())
            .map(groupsMapper::toDto);
    }


    /**
     * Get one groups by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GroupsDTO> findOne(Long id) {
        log.debug("Request to get Groups : {}", id);
        return groupsRepository.findById(id)
            .map(groupsMapper::toDto);
    }

    /**
     * Delete the groups by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Groups : {}", id);
        groupsRepository.deleteById(id);
    }
}
